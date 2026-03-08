package com.dev.socialmedia.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent; // Comment cha

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> replies; // Các câu trả lời

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}