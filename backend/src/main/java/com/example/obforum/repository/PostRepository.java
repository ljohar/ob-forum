package com.example.obforum.repository;

import com.example.obforum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByUserId (Long userId);

    Long countPostsByThreadId (Long threadId);

    Long findByThreadId (Long threadId);

    List<Post> findAllByThreadIdOrderByFixedDesc(Long threadId);

    List<Post> findByOrderByLikesCountDesc();

    List<Post> findByOrderByCreateDateTimeAsc();


}
