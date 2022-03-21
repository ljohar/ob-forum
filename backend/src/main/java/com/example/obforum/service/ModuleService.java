package com.example.obforum.service;

import com.example.obforum.model.Module;

import java.util.List;
import java.util.Optional;

/**
 * CRUD: Create, Retrieve / Read, Update, Delete
 */

public interface ModuleService {

    Optional<Module> findById(Long id);

    List<Module> findAll();

    Module save(Module module);

    boolean deleteById(Long id);
}
