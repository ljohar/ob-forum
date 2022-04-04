package com.example.obforum.service;

import com.example.obforum.model.Post;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final PostService postService;


    public NotificationService(SimpMessagingTemplate simpMessagingTemplate, PostService postService, ThreadService threadService, PostService postService1) {
        this.simpMessagingTemplate = simpMessagingTemplate;

        this.postService = postService1;
    }

    public void sendGlobalNotification(Long userId) {
        Post post = postService.findByUserId(userId);
        simpMessagingTemplate.convertAndSend("topic/global-notifications", post);
    }
}
