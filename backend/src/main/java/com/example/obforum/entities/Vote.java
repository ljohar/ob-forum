package com.example.obforum.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Votes")

public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;
}
