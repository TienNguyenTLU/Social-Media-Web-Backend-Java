package com.dev.socialmedia.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "follows", uniqueConstraints = {@UniqueConstraint(columnNames = {"follower_id", "following_id"})})
@Getter
@Setter
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne @JoinColumn(name = "following_id")
    private User following;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
