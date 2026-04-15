package com.amitesh.personal_portfolio_server.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwcrmzpcn",
                "api_key", "322748174824427",
                "api_secret", "P3EOm093bj-SDnWiLsRuiqF9OHs"
        ));
    }

    public Map upload(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );
            return uploadResult; // contains public_id + url
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }
    }

    // ✅ Update (Overwrite existing image)
    public Map update(MultipartFile file, String publicId) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "public_id", publicId,
                            "overwrite", true
                    )
            );
            return uploadResult;
        } catch (IOException e) {
            throw new RuntimeException("Image update failed");
        }
    }

    // ✅ Delete
    public String delete(String publicId) {
        try {
            Map result = cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap()
            );
            return result.get("result").toString(); // "ok"
        } catch (IOException e) {
            throw new RuntimeException("Image deletion failed");
        }
    }
}