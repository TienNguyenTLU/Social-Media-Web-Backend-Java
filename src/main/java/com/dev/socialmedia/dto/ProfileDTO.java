package com.dev.socialmedia.dto;

import com.dev.socialmedia.models.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
public class ProfileDTO {
    private Long id;
    private String username;
    private String email;
    private String displayName;
    private String avatar;
    private String bio;
    private String background;
    private List<Post> Posts;
    private Set<Post> savedPosts;
    private Set<Post> likedPosts;
    private LocalDateTime createdDate;
}
