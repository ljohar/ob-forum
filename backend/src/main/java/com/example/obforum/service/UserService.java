package com.example.obforum.service;


import com.example.obforum.model.Post;
import com.example.obforum.model.User;
import com.example.obforum.dto.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
    boolean existsByEmail(String email);
    User findByVotesId (Long voteId);
    User findByUsername(String username);
    Post findByPostsId(Long postId);

}
