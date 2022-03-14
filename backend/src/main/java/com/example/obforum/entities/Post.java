package com.example.obforum.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

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

    @Lob
    private String content;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    private boolean isFixed;

    //RELATIONSHIPS

    //USERS

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_user"))
    private User user;

    //VOTES

    @OneToMany
    private List<Vote> votes = new ArrayList<>();

    public Post() {
    }

    public Post(Long id, String content, LocalDateTime createDateTime, boolean isFixed) {
        this.id = id;
        this.content = content;
        this.createDateTime = createDateTime;
        this.isFixed = isFixed;
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
