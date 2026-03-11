package com.dev.socialmedia.controllers;
import com.dev.socialmedia.dto.PostDTO;
import com.dev.socialmedia.dto.PostResponse;
import com.dev.socialmedia.models.Category;
import com.dev.socialmedia.models.Post;
import com.dev.socialmedia.repositories.UserRepository;
import com.dev.socialmedia.services.CloudinaryService;
import com.dev.socialmedia.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final CloudinaryService cloudinaryService;
    private final PostService postService;
    public PostController(CloudinaryService cloudinaryService, PostService postService) {
        this.cloudinaryService = cloudinaryService;
        this.postService = postService;
    }
    @GetMapping("/all")
    ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(
            HttpServletRequest request,
            @RequestPart("data") PostDTO postDTO,
            @RequestPart("categories") Set<Long> categories,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        String username = (String) request.getAttribute("authenticatedUser");
        String imageUrl;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = cloudinaryService.uploadFile(imageFile, "posts");
        } else {
            imageUrl = null;
        }
        Post post = postService.createPost(postDTO, imageUrl, username, categories);
        return ResponseEntity.ok(new PostResponse(post));
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, HttpServletRequest request) {
        String username = (String) request.getAttribute("authenticatedUser");
        postService.deletePost(postId, username);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId,
            HttpServletRequest request,
            @RequestPart("data") PostDTO postDTO,
            @RequestPart("categories") Set<Long> categories,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        String username = (String) request.getAttribute("authenticatedUser");
        String imageUrl;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = cloudinaryService.uploadFile(imageFile, "posts");
        } else {
            imageUrl = null;
        }
        Post post = postService.updatePost(postId, postDTO, imageUrl, categories, username );
        return ResponseEntity.ok(new PostResponse(post));
    }
}
