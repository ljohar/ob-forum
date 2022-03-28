package com.example.obforum.controller;

import com.example.obforum.model.Post;
import com.example.obforum.model.Thread;
import com.example.obforum.model.Topic;
import com.example.obforum.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
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
     */

    @GetMapping("/search/threadId/{threadId}")
    public List<Post> findAllByThreadId(@PathVariable Long threadId){
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
     * GET http://localhost:8080/posts/search/postId/{postId}
     */

    @GetMapping("/search/threadId/{threadId}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        Optional<Post> postOpt = postService.findById(id);
        if(postOpt.isPresent())
            return ResponseEntity.ok(postOpt.get());

        return ResponseEntity.notFound().build();

    }

    /**
     * Create a Post
     * POST http://localhost:8080/posts
     */
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        if (post.getId() != null)
            return ResponseEntity.badRequest().build();
        post.getThread().setPostsCount(postService.countPostsByThreadId(post.getThread().getId())+1);
        return ResponseEntity.ok(postService.save(post));
    }

    /**
     * Update a Post
     * PUT http://localhost:8080/posts
     */
    @PostAuthorize("returnObject.username==authentication.principal.username or hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Post> update(@RequestBody Post post) {
        if (post.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(postService.save(post));
    }

    /**
     * Delete a post
     * DELETE http://localhost:8080/threads
     */

    @PostAuthorize("returnObject.username==authentication.principal.username or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean result = postService.deleteById(id);
        if(result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }


}
