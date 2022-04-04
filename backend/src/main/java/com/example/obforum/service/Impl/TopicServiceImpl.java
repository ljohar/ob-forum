package com.example.obforum.service.Impl;

import com.example.obforum.model.Topic;
import com.example.obforum.model.User;
import com.example.obforum.repository.TopicRepository;
import com.example.obforum.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<Topic> findById(Long id) {
        if(id==null || id <=0)
            return Optional.empty();
        return topicRepository.findById(id);
    }


    @Override
    public Topic save(Topic topic) {
        if(topic==null)
            throw new IllegalArgumentException("Invalid argument");
        return topicRepository.save(topic);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id==null || !topicRepository.existsById(id))
        return false;

        topicRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Topic> findAllByOrderByFixedDesc() {
        return topicRepository.findAllByOrderByFixedDesc();
    }

    @Override
    public Set<Topic> findAllByUsersId(Long userId) {

        return topicRepository.findAllByUsersId(userId);
    }

    @Override
    public List<Topic> findAllByUserUsername(String username) {
        return topicRepository.findAllByUsersUsernameOrderByFixedDesc(username);
    }

    @Override
    public Set<Topic> findAllByUsers(User user) {
        return topicRepository.findAllByUsers(user);
    }



    @Override
    public List<Topic> findAllByCoursesIdOrderByFixedDesc(Long courseId) {
        return topicRepository.findAllByCoursesIdOrderByFixedDesc(courseId);
    }

    @Override
    public List<Topic> findAllByModulesIdOrderByFixedDesc(Long moduleId) {
        return topicRepository.findAllByModulesIdOrderByFixedDesc(moduleId);
    }


}
