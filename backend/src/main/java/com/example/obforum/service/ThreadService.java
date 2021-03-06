package com.example.obforum.service;

import com.example.obforum.model.Thread;
import com.example.obforum.model.Topic;

import java.util.List;
import java.util.Optional;

/**
 * CRUD: Create, Retrieve / Read, Update, Delete
 */

public interface ThreadService {

    Optional<Thread> findById(Long id);

    List<Thread> findAll();

    Thread save(Thread thread);

    boolean deleteById(Long id);

    List<Thread> findAllByOrderByFixedDesc();

    List<Thread> findAllByTopicIdOrderByFixedDesc(Long topicId);



//    Thread findOne(Long id);
//
//    Set<Thread> findAllByOrderCreateDateTimeDesc();
//
//    Set<Thread> findByUserId(Long id);
//
//    Set<Thread> findByTopicId(Long id);
}
