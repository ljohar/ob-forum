package com.example.obforum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Topics")

public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;

    private String synopsis;

    private boolean fixed;



    //RELATIONSHIPS


    //THREADS
    @OneToMany(fetch = FetchType.EAGER)
    private List<Thread> threads = new ArrayList<>();

    //MODULES
    @JsonIgnoreProperties(value = {"topics"})
    @ManyToMany
    @JoinTable(name = "TOPIC_MODULES",
            joinColumns = {
                    @JoinColumn(name = "TOPIC_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "MODULE_ID") })
    private List<Module> modules = new ArrayList<>();

    //COURSES
    @JsonIgnoreProperties(value = {"topics"})
    @ManyToMany
    @JoinTable(name = "TOPIC_COURSES",
            joinColumns = {
                    @JoinColumn(name = "TOPIC_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "COURSE_ID") })

    private Set<Course> courses;

    //USERS
    //BIDIRECTIONAL
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

    public boolean isFixed() {
        return fixed;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Topic() {
    }
}
