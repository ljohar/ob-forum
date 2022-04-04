package com.example.obforum.controller;

import com.example.obforum.model.Post;
import com.example.obforum.model.Role;
import com.example.obforum.model.User;
import com.example.obforum.service.PostService;
import com.example.obforum.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostService postService;

    @Autowired
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * GET http://localhost:8080/posts
     */
    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }

    /**
     * GET http://localhost:8080/posts/search/threadId/{threadId}
     * @param  threadId
     * @return
     */

    @ApiOperation("Search posts by thread id, sort fixed ones first")

    @GetMapping("/search/threadId/{threadId}")
    public List<Post> findAllByThreadId(@ApiParam("Thread primary key type Long") @PathVariable Long threadId){
        return postService.findAllByThreadIdOrderByFixedDesc(threadId);

    }

    /**
     * Most upvoted posts
     * http://localhost/foro/respuestas?sort=totalVotosPositivos&order=desc
     */
    @GetMapping("/foro/respuestas?sort=totalVotosPositivos&order=desc")
    public List<Post> findByMostUpvoted(){

        return postService.findByOrderByLikesCountDesc();
    }

    /**
     * http://localhost/foro/respuestas?sort=updated_at&order=asc
     */

    @GetMapping("/foro/respuestas?sort=updated_at&order=asc")
    public List<Post> findByOlderPosts(){
        return postService.findByOrderByCreateDateTimeAsc();
    }



    /**
     * Create a Post
     * POST http://localhost:8080/posts
     */
    @PostMapping
    public ResponseEntity<Post> createPost(Authentication authentication, @RequestBody Post post){
        if (post.getId() != null)
            return ResponseEntity.badRequest().build();

        User currentlyAuthenticatedUser = userService.findByUsername(authentication.getName());

        for (Role role : currentlyAuthenticatedUser.getRoles()){
            if (role.getName() == "USER"){
                post.setUser(currentlyAuthenticatedUser);
                break;
            }
        }

        post.getThread().setPostsCount(postService.countPostsByThreadId(post.getThread().getId())+1);
        return ResponseEntity.ok(postService.save(post));
    }

    /**
     * Update a Post
     * PUT http://localhost:8080/posts
     */
//    @PostAuthorize("returnObject.username==authentication.principal.username or hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Post> update(@RequestBody Post post) {
        if (post.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(postService.save(post));
    }

    /**
     * Delete a post
     * DELETE http://localhost:8080/posts
     */


//    @ApiIgnore
//    @PostAuthorize("returnObject.username==authentication.principal.username or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean result = postService.deleteById(id);
        if(result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }


}
