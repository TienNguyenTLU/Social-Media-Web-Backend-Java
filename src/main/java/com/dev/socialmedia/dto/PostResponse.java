package com.dev.socialmedia.dto;
import com.dev.socialmedia.models.Category;
import com.dev.socialmedia.models.Post;
import java.util.Set;
import java.util.stream.Collectors;

public class PostResponse {
    private Long id;
    private String content;
    private String imageUrl;
    private String authorName;
    private Set<String> categoryNames;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.authorName = post.getAuthor().getUsername();
        this.categoryNames = post.getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }
}