package com.example.obforum.controller;


import com.example.obforum.model.Post;
import com.example.obforum.model.Topic;
import com.example.obforum.model.Vote;
import com.example.obforum.service.UserService;
import com.example.obforum.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteContoller {

    private final VoteService voteService;
    private final UserService userService;

    public VoteContoller(VoteService voteService, UserService userService) {
        this.voteService = voteService;
        this.userService = userService;
    }

    /**
     * GET http://localhost:8080/votes
     */
    @GetMapping
    public List<Vote> findAll() {
        return voteService.findAll();
    }

    /**
     * GET http://localhost:8080/votes/search/threadId/{threadId}
     * GET http://localhost:8080/votes/search/postId/{postId}
     */


    @GetMapping("/search/threadId/{threadId}")
    public List<Vote> findAllByThreadId(@PathVariable Long threadId){
        return voteService.findAllByThreadId(threadId);
    }

    @GetMapping("/search/postId/{postId}")
    public List<Vote> findAllByPostId(@PathVariable Long postId){
        return voteService.findAllByPostId(postId);
    }


    /**
     * POST http://localhost:8080/votes/likes
     * POST http://localhost:8080/votes/dislikes
     */
    @PostMapping("/likes")
    public ResponseEntity<Vote> likeCount(@RequestBody Vote vote){
        if (vote.getId() != null)
            return ResponseEntity.badRequest().build();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

//        if ((voteService.countVotesIsLikedTrueByPostIdAndUserId(vote.getPost().getId(), userService.findByUsername(currentUserName).getId())) > 0)
//            return ResponseEntity.badRequest().build();
//
//        Long postId = vote.getPost().getId();



        vote.getPost().setLikesCount(voteService.countVotesIsLikedTrueByPostId(vote.getPost().getId())+1);

        vote.setLiked(true);

        return ResponseEntity.ok(voteService.save(vote));
    }

    @PostMapping("/dislikes")
    public ResponseEntity<Vote> dislikeCount(@RequestBody Vote vote){
        if (vote.getId() != null)
            return ResponseEntity.badRequest().build();

//        if (doesUserAlreadyVoted(vote))
//            return ResponseEntity.badRequest().build();



        vote.getPost().setDislikesCount(voteService.countVotesIsLikedFalseByPostId(vote.getPost().getId())+1);

        vote.setLiked(false);

        return ResponseEntity.ok(voteService.save(vote));
    }

    public boolean doesUserAlreadyVoted(Vote vote){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        if ((voteService.countVotesLikesByPostIdAndUserId(vote.getPost().getId(), userService.findByUsername(currentUserName).getId())) > 0)
            return true;
        return false;
    }

    /**
     * Update a vote
     * PUT http://localhost:8080/votes
     */
    @PostAuthorize("returnObject.username==authentication.principal.username or hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Vote> update(@RequestBody Vote vote) {
        if (vote.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(voteService.save(vote));
    }

    /**
     * Delete a vote
     * DELETE http://localhost:8080/votes
     */

    @PostAuthorize("returnObject.username==authentication.principal.username or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean result = voteService.deleteById(id);
        if(result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }

}
