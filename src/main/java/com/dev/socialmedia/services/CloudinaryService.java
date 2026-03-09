package com.dev.socialmedia.services;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    public String uploadFile(MultipartFile file, String folderName)
    {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "social_media/" + folderName, // Tạo cây thư mục
                            "resource_type", "auto" // Tự động nhận diện image, video...
                    ));
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Upload file thất bại: " + e.getMessage());
        }
    }
    public void deleteFile(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Xóa file thất bại: " + e.getMessage());
        }
    }
}
