package com.example.obforum.service.Impl;

import com.example.obforum.model.Vote;
import com.example.obforum.repository.VoteRepository;
import com.example.obforum.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "voteService")
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Optional<Vote> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return voteRepository.findById(id);
    }

    @Override
    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote save(Vote vote) {
        if(vote == null)
            throw new IllegalArgumentException("Invalid argument");
        return voteRepository.save(vote);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !voteRepository.existsById(id))
            return false;
        voteRepository.deleteById(id);
        return true;
    }

    @Override
    public int countVotesLikesByPostIdAndUserId(Long postId, Long userId) {
        return voteRepository.countVotesLikesByPostIdAndUserId(postId, userId);
    }

    @Override
    public int countVotesLikesByPostId(Long postId) {
        return voteRepository.countVotesLikesByPostId(postId);
    }

    @Override
    public int countVotesDislikesByPostId(Long postId) {
        return voteRepository.countVotesDislikesByPostId(postId);
    }

    @Override
    public Long countVotesIsLikedTrueByPostId(Long postId) {
        return voteRepository.countVotesIsLikedTrueByPostId(postId);
    }

    @Override
    public Long countVotesIsLikedFalseByPostId(Long postId) {
        return voteRepository.countVotesIsLikedFalseByPostId(postId);
    }

    @Override
    public Long countVotesIsLikedTrueByPostIdAndUserId(Long postId, Long userId) {
        return countVotesIsLikedTrueByPostIdAndUserId(postId, userId);
    }

    @Override
    public List<Vote> findAllByThreadId(Long threadId) {
        return voteRepository.findAllByThreadId(threadId);
    }

    @Override
    public List<Vote> findAllByPostId(Long postId) {
        return voteRepository.findAllByPostId(postId);
    }
}
