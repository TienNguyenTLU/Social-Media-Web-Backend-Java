package com.dev.socialmedia.dto;

import com.dev.socialmedia.models.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
public class PostDTO {
    Long id;
    String content;
    String imageUrl;
    Integer likesCount;
    LocalDateTime createdAt;
    Set<Long> categoriesIds;
}
