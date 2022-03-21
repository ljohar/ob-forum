package com.example.obforum.service;

import com.example.obforum.model.Topic;
import com.example.obforum.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * CRUD: Create, Retrieve / Read, Update, Delete
 */

public interface TopicService {

    Optional<Topic> findById(Long id);
    
    List<Topic> findAll();
    
    Topic save(Topic topic);
    
    boolean deleteById(Long id);

    Set<Topic> findAllByUsersId (Long userId);

    Set<Topic> findAllByUsersUsername(String username);

    Set<Topic> findAllByUsers(User user);
}
