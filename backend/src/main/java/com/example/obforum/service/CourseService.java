package com.example.obforum.service;

import com.example.obforum.model.Course;

import java.util.List;
import java.util.Optional;

/**
 * CRUD: Create, Retrieve / Read, Update, Delete
 */

public interface CourseService {

    Optional<Course> findById(Long id);

    List<Course> findAll();

    Course save(Course course);

    boolean deleteById(Long id);
}
