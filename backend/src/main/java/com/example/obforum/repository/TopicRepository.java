package com.example.obforum.repository;

import com.example.obforum.model.Topic;
import com.example.obforum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{

    Set<Topic> findAllByUsersId(Long userId);

    Set<Topic> findAllByUsers(User user);

    Set<Topic> findAllByUsersUsername(String username);
}