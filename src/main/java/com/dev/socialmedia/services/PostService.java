package com.dev.socialmedia.services;
import com.dev.socialmedia.dto.PostDTO;
import com.dev.socialmedia.models.Category;
import com.dev.socialmedia.models.Post;
import com.dev.socialmedia.repositories.CategoryRepository;
import com.dev.socialmedia.repositories.PostRepository;
import com.dev.socialmedia.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
            this.categoryRepository = categoryRepository;
    }
    public Post createPost(PostDTO postDTO, String imageUrl,String username, Set<Long> caregoryIds ) {
        Post post = new Post();
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(caregoryIds));
        post.setAuthor(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found")));
        post.setContent(postDTO.getContent());
        post.setCategories(categories);
        post.setImageUrl(imageUrl);
        return postRepository.save(post);
    }
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public void deletePost(Long postId, String username) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        postRepository.delete(post);
    }
    public Post updatePost(Long postId, PostDTO postDTO, String username, Set<Long> categoryIds, String imageUrl) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoryIds));
        post.setContent(postDTO.getContent());
        post.setCategories(categories);
        if (imageUrl != null) {
            post.setImageUrl(imageUrl);
        }
        return postRepository.save(post);
    }
}
