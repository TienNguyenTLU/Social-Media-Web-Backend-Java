package com.dev.socialmedia.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private String content;
    private boolean isRead = false;
    @ManyToOne private User recipient; // Người nhận
    @ManyToOne private User actor;     // Người gây ra hành động
    private Long targetId;            // ID của Post hoặc Comment liên quan
}

