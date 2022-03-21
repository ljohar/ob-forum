package com.example.obforum.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Topics")

public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String synopsis;



    //RELATIONSHIPS

    //THREADS

    @OneToMany(fetch = FetchType.EAGER)
    private List<Thread> threads = new ArrayList<>();

    //MODULES
    @OneToMany
    private List<Module> modules = new ArrayList<>();

    //COURSES

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //USERS
    @ManyToMany(mappedBy = "topics")
    private List<User> users = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Topic() {
    }
}
