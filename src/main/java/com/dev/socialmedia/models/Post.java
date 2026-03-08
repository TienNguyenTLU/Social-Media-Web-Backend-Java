package com.dev.socialmedia.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    @ManyToMany
    @JoinTable(name = "post_hashtags")
    private Set<Hashtag> hashtags;
    @ManyToMany
    @JoinTable(name = "post_categories")
    private Set<Category> categories;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @CreationTimestamp
    private LocalDateTime createdAt;
}



