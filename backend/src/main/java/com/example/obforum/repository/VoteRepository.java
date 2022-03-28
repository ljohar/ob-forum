package com.example.obforum.repository;

import com.example.obforum.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    int countVotesLikesByPostId (Long postId);
    int countVotesDislikesByPostId (Long postId);
    Long countVotesIsLikedTrueByPostId (Long postId);
    Long countVotesIsLikedFalseByPostId (Long postId);
    Long countVotesIsLikedTrueByPostIdAndUserId (Long postId, Long userId);
    int countVotesLikesByPostIdAndUserId(Long postId, Long userId);
    List<Vote> findAllByThreadId(Long threadId);
    List<Vote> findAllByPostId(Long postId);


}
