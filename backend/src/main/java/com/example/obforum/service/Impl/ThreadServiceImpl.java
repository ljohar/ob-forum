package com.example.obforum.service.Impl;

import com.example.obforum.model.Thread;
import com.example.obforum.repository.ThreadRepository;
import com.example.obforum.service.ThreadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;

    public ThreadServiceImpl(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public Optional<Thread> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();

        return threadRepository.findById(id);
    }

    @Override
    public List<Thread> findAll() {
        return threadRepository.findAll();
    }

    @Override
    public Thread save(Thread thread) {
        if(thread == null)
            throw new IllegalArgumentException("Argumento Direction incorrecto");

        return threadRepository.save(thread);
    }

    @Override
    public boolean deleteById(Long id) {
        if(id == null || !threadRepository.existsById(id))
            return false;

        threadRepository.deleteById(id);

        return true;

    }
}
