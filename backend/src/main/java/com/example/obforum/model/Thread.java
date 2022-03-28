package com.example.obforum.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Threads")
public class Thread implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    private String content;

    @Column(
            name = "created_on",
//            nullable = false,
            updatable = false
    )
    @CreationTimestamp
    private LocalDateTime createDateTime;

    private boolean fixed;

    private Long postsCount;




    //RELATIONSHIPS

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    //USERS

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_thread_user"))
    private User user;

    //ANSWERS

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    //VOTES

    @OneToMany
    private List<Vote> votes = new ArrayList<>();


    public Thread() {
    }


    public Topic getTopic() {
        return topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Long postsCount) {
        this.postsCount = postsCount;
    }

    public boolean isFixed() {
        return fixed;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }




}
