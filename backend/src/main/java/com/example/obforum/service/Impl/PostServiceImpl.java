package com.example.obforum.service.Impl;

import com.example.obforum.model.Post;
import com.example.obforum.repository.PostRepository;
import com.example.obforum.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
        if(post == null)
            throw new IllegalArgumentException("Invalid argument");
        return postRepository.save(post);
    }


    @Override
    public boolean deleteById(Long id) {
        if(id == null || !postRepository.existsById(id))
            return false;

        postRepository.deleteById(id);

        return true;
    }

    @Override
    public Long countPostsByThreadId(Long threadId) {
        return postRepository.countPostsByThreadId(threadId);
    }

    @Override
    public Long findByThreadId(Long threadId) {
        return postRepository.findByThreadId(threadId);
    }

    @Override
    public Post findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public List<Post> findAllByThreadIdOrderByFixedDesc(Long threadId) {
        return postRepository.findAllByThreadIdOrderByFixedDesc(threadId);
    }

    @Override
    public List<Post> findByOrderByLikesCountDesc() {
        return postRepository.findByOrderByLikesCountDesc();
    }

    @Override
    public List<Post> findByOrderByCreateDateTimeAsc() {
        return postRepository.findByOrderByCreateDateTimeAsc();
    }


}
