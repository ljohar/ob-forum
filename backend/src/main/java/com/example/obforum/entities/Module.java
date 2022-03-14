package com.example.obforum.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Modules")

public class Module implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String synopsis;

    @Lob
    private byte[] icon;

    //RELATIONSHIPS

    //THREADS

    @OneToMany
    private List<Thread> threads = new ArrayList<>();

    public Module() {
    }

    public Module(Long id, String name, String synopsis, byte[] icon) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.icon = icon;
    }
}
