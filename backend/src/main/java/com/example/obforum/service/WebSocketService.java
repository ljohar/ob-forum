package com.example.obforum.service;

import com.example.obforum.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;

    @Autowired
    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate, NotificationService notificationService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyClient (final Long userId){
        notificationService.sendGlobalNotification(userId);

        simpMessagingTemplate.convertAndSend("/topic/threads");
    }

}
