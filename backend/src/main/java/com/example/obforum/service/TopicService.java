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

    Topic save(Topic topic);
    
    boolean deleteById(Long id);

    List<Topic> findAllByOrderByFixedDesc();

    Set<Topic> findAllByUsersId (Long userId);

    List<Topic> findAllByUserUsername(String username);

    Set<Topic> findAllByUsers(User user);



    List<Topic> findAllByCoursesIdOrderByFixedDesc(Long courseId);

    List<Topic> findAllByModulesIdOrderByFixedDesc(Long moduleId);
}
