package com.example.obforum.service;

import com.example.obforum.model.Post;

import java.util.List;
import java.util.Optional;

/**
 * CRUD: Create, Retrieve / Read, Update, Delete
 */

public interface PostService {

    Optional<Post> findById(Long id);

    List<Post> findAll();

    Post save(Post post);

    boolean deleteById(Long id);

    Long countPostsByThreadId (Long threadId);

    Long findByThreadId (Long threadId);

    Post findByUserId(Long userId);

    List<Post> findAllByThreadIdOrderByFixedDesc(Long threadId);

    List<Post> findByOrderByLikesCountDesc();

    List<Post> findByOrderByCreateDateTimeAsc();
}
