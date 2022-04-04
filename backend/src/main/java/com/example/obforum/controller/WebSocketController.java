package com.example.obforum.controller;


import com.example.obforum.model.Post;
import com.example.obforum.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    private final WebSocketService webSocketService;

    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @PostMapping("/send-post")
    public void sendMessage(@RequestBody final Post post) {
        webSocketService.notifyClient(post.getUser().getId());
    }



}
