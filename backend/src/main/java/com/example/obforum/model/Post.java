package com.example.obforum.model;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Posts")


public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private boolean isFixed = false;

    private Long likesCount;

    private Long dislikesCount;

    //RELATIONSHIPS

    //USERS

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_user"))
    private User user;

    //THREADS

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;

    //VOTES

    @OneToMany
    private List<Vote> votes = new ArrayList<>();


    public Thread getThread() {
        return thread;
    }

    public Post() {
    }

    public Post(Long id, String content, LocalDateTime createDateTime, boolean isFixed) {
        this.id = id;
        this.content = content;
        this.createDateTime = createDateTime;
        this.isFixed = isFixed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isFixed() {
        return isFixed;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }

    public Long getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(Long dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createDateTime=" + createDateTime +
                ", isFixed=" + isFixed +
                '}';
    }
}
