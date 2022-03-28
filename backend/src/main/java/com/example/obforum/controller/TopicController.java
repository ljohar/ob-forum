package com.example.obforum.controller;


import com.example.obforum.model.Topic;
import com.example.obforum.service.TopicService;
import com.example.obforum.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
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

    /**
     * GET http://localhost:8080/topics
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Topic> findAll(){
        return topicService.findAllByOrderByFixedDesc();
    }

    @GetMapping("/myProfile")
    public Set<Topic> getTopicsByWhoIsRequesting(Authentication authentication)
    {
        return topicService.findAllByUsersUsername(authentication.getName());
    }

    /**
     * Create a new Topic
     * POST http://localhost:8080/threads
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Topic> createThread(@RequestBody Topic topic){
        if (topic.getId() != null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(topicService.save(topic));
    }

    /**
     * Update a topic
     * PUT http://localhost:8080/threads
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Topic> update(@RequestBody Topic topic) {
        if (topic.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(topicService.save(topic));
    }

    /**
     * DELETE a thread
     * DELETE http://localhost:8080/threads
     */

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean result = topicService.deleteById(id);
        if(result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }


    /**
     * GET http://localhost:8080/topics/search/moduleId/{moduleId}
     * GET http://localhost:8080/topics/search/courseId/{courseId}
     */


    @GetMapping("/search/moduleId/{moduleId}")
    public List<Topic> findAllByModulesId(@PathVariable Long moduleId){
        return topicService.findAllByModulesIdOrderByFixedDesc(moduleId);
    }

    @GetMapping("/search/courseId/{courseId}")
    public List<Topic> findAllByCoursesId(@PathVariable Long courseId){
        return topicService.findAllByCoursesIdOrderByFixedDesc(courseId);
    }






}
