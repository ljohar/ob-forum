package com.example.obforum.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Threads")
public class Thread implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private boolean isFixed;

    //RELATIONSHIPS

    //USERS

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_thread_user"))
    private User user;

    //POSTS

    @OneToMany
    private List<Post> posts = new ArrayList<>();

    //VOTES

    @OneToMany
    private List<Vote> votes = new ArrayList<>();



    public User getUser() {
        return user;
    }

    public Thread() {
    }

    public Thread(Long id, String title, String content, LocalDateTime createDateTime, boolean isFixed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDateTime = createDateTime;
        this.isFixed = isFixed;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDateTime=" + createDateTime +
                ", isFixed=" + isFixed +
                '}';
    }
}
