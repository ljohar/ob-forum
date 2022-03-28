package com.example.obforum.repository;

import com.example.obforum.model.Post;
import com.example.obforum.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByEmail(String email);
    User findByVotesId (Long voteId);
    Post findByPostsId(Long postId);

}