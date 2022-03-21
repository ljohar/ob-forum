package com.example.obforum.service;

import com.example.obforum.model.Vote;

import java.util.List;
import java.util.Optional;

/**
 * CRUD: Create, Retrieve / Read, Update, Delete
 */

public interface VoteService {

    Optional<Vote> findById(Long id);

    List<Vote> findAll();

    Vote save(Vote vote);

    boolean deleteById(Long id);
}
