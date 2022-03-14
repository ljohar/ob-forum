package com.example.obforum.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Topics")

public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] icon;

    @OneToMany
    private List<Module> modules = new ArrayList<>();

    public Topic() {
    }

    public Topic(Long id, String name, byte[] icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon=" + Arrays.toString(icon) +
                '}';
    }
}
