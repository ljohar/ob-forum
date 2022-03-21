package com.example.obforum.controller;

import com.example.obforum.model.Topic;
import com.example.obforum.service.TopicService;
import com.example.obforum.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/topics")

public class TopicController {
    private final TopicService topicService;
    private final UserService userService;

    public TopicController(TopicService topicService, UserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(" ")
    public List<Topic> findAll(){
        return topicService.findAll();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    }


//    @GetMapping("/myPerfile/{id}")
//    public Set<Topic> findAllByUserId(@PathVariable Long userId){
//        return topicService.findAllByUsersId(userId);
//    }


//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/myPerfile")
//    public Set<Topic> findAllByUsers(@AuthenticationPrincipal User user){
//        return topicService.findAllByUsersId(user.getId());
//
//    }


    @GetMapping("/myProfile")
    public Set<Topic> getTopicsByWhoIsRequesting(Authentication authentication)
    {
        return topicService.findAllByUsersUsername(authentication.getName());

    }




}
