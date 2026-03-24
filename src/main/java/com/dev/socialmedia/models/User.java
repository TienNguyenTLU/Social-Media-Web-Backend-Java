package com.dev.socialmedia.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "username"), @Index(columnList = "email")})
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String displayName;
    private String avatar;
    private String password;
    private String background;
    private String bio;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "author")
    private List<Post> posts;
    @ManyToMany
    @JoinTable(name = "user_saved_posts")
    private Set<Post> savedPosts;
    @ManyToMany
    @JoinTable(name = "user_liked_posts")
    private Set<Post> likedPosts;

    public User(Long followingId) {
    }
}