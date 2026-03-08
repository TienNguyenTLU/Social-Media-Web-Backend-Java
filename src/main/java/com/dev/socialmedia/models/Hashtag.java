package com.dev.socialmedia.models;

import jakarta.persistence.*;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true)
    private String name; // ví dụ: #java

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}