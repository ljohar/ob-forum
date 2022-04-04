package com.example.obforum.controller;

import com.example.obforum.model.Thread;
import com.example.obforum.model.Topic;
import com.example.obforum.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/threads")
public class ThreadController {

    @Autowired
    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    /**
     * GET http://localhost:8080/threads
     */

    @GetMapping
    public ResponseEntity<List<Thread>> findAll(){
        return ResponseEntity.ok(threadService.findAll());
    }


    /**
     * GET http://localhost:8080/threads/search/threadId/{threadId}
     */

    @GetMapping("/search/threadId/{threadId}")
    public ResponseEntity<Thread> findById(@PathVariable Long id){
        Optional<Thread> threadOpt = threadService.findById(id);
        if(threadOpt.isPresent())
            return ResponseEntity.ok(threadOpt.get());
        return ResponseEntity.notFound().build();

    }


    /**
     * Create a new Thread
     * POST http://localhost:8080/threads
     */

    @PostMapping
    public ResponseEntity<Thread> createThread(@RequestBody Thread thread){
        if (thread.getId() != null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(threadService.save(thread));
    }


    /**
     * Update a thread
     * PUT http://localhost:8080/threads
     */

    @PostAuthorize("returnObject.username==authentication.principal.username or hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Thread> update(@RequestBody Thread thread) {
        if (thread.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(threadService.save(thread));
    }

    /**
     * Delete a thread
     * DELETE http://localhost:8080/threads
     */

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        boolean result = threadService.deleteById(id);
        if(result)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }


}
