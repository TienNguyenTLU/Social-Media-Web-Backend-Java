package com.dev.socialmedia.controllers;

import com.dev.socialmedia.dto.ProfileDTO;
import com.dev.socialmedia.models.User;
import com.dev.socialmedia.services.AuthService;
import com.dev.socialmedia.services.CloudinaryService;
import com.dev.socialmedia.services.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final CloudinaryService cloudinaryService;
    public ProfileController(ProfileService profileService, CloudinaryService cloudinaryService) {
        this.profileService = profileService;
        this.cloudinaryService = cloudinaryService;
    }
    @GetMapping("/me")
    public ProfileDTO getMyProfile(HttpServletRequest request) {
        String username = (String) request.getAttribute("authenticatedUser");
        return profileService.getCurrentUser(username);
    }
    @PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileDTO> updateProfile(
            HttpServletRequest request,
            @RequestPart(value = "data", required = false) @Valid ProfileDTO profileDTO, // Nhận JSON data
            @RequestPart(value = "avatar", required = false) MultipartFile avatarFile, // File ảnh đại diện
            @RequestPart(value = "background", required = false) MultipartFile backgroundFile // File ảnh bìa
    ) {
        String username = (String) request.getAttribute("authenticatedUser");
        if (profileDTO == null) {
            profileDTO = new ProfileDTO(); // Tạo một đối tượng trống nếu không có data
        }
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String avatarUrl = cloudinaryService.uploadFile(avatarFile, "avatars");
            profileDTO.setAvatar(avatarUrl);
        }
        if (backgroundFile != null && !backgroundFile.isEmpty()) {
            String backgroundUrl = cloudinaryService.uploadFile(backgroundFile, "backgrounds");
            profileDTO.setBackground(backgroundUrl);
        }
        ProfileDTO updatedProfile = profileService.EditProfile(username, profileDTO);
        return ResponseEntity.ok(updatedProfile);
    }
}
