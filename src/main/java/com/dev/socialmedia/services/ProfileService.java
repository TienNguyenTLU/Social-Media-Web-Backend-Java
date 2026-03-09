package com.dev.socialmedia.services;

import com.dev.socialmedia.dto.ProfileDTO;
import com.dev.socialmedia.exceptions.ResourceNotFoundException;
import com.dev.socialmedia.models.User;
import com.dev.socialmedia.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final UserRepository userRepo;
    private final CloudinaryService cloudinaryService;
    public ProfileService(UserRepository userRepo, CloudinaryService cloudinaryService) {
        this.userRepo = userRepo;
        this.cloudinaryService = cloudinaryService;
    }
    public ProfileDTO UserMapping(User user) {
        var dto = new ProfileDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setAvatar(user.getAvatar());
        dto.setBackground(user.getBackground());
        dto.setBio(user.getBio());
        dto.setPosts(user.getPosts());
        dto.setSavedPosts(user.getSavedPosts());
        dto.setLikedPosts(user.getLikedPosts());
        dto.setCreatedDate(user.getCreatedDate());
        return dto;
    }
    public ProfileDTO getCurrentUser(String username) {
        User user = userRepo.findProfileByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapping(userRepo.findByUsername(username).get());
    }
    public ProfileDTO EditProfile(String username, ProfileDTO profileDTO) {
        // 1. Tìm user hoặc ném lỗi 404
        User user = userRepo.findProfileByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        // 2. Chỉ cập nhật nếu dữ liệu trong DTO không null hoặc không trống
        if (profileDTO.getDisplayName() != null) {
            user.setDisplayName(profileDTO.getDisplayName());
        }
        // Quan trọng: Chỉ set URL ảnh nếu có ảnh mới được upload thành công từ Controller
        if (profileDTO.getAvatar() != null && !profileDTO.getAvatar().isEmpty()) {
            user.setAvatar(profileDTO.getAvatar());
        }
        if (profileDTO.getBackground() != null && !profileDTO.getBackground().isEmpty()) {
            user.setBackground(profileDTO.getBackground());
        }
        if (profileDTO.getBio() != null) {
            user.setBio(profileDTO.getBio());
        }
        // 3. Lưu vào DB
        User updatedUser = userRepo.save(user);
        // 4. Map ngược lại DTO để trả về cho Frontend
        return UserMapping(updatedUser);
    }
}
