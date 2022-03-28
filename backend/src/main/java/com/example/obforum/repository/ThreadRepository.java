package com.example.obforum.repository;

import com.example.obforum.model.Post;
import com.example.obforum.model.Thread;
import com.example.obforum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    List<Thread> findAllByOrderByFixedDesc();

    List<Thread> findAllByTopicIdOrderByFixedDesc(Long topicId);

}
