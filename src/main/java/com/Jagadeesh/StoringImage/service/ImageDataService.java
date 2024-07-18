package com.Jagadeesh.StoringImage.service;

import com.Jagadeesh.StoringImage.entity.ImageData;
import com.Jagadeesh.StoringImage.repository.ImageDataRepository;
import com.Jagadeesh.StoringImage.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        ImageData imageData = imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageName(ImageUtils.compressImage(imageBytes))
                .build());
        if (imageData != null) {
            return "Image uploaded successfully with ID: " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String name) {
        Optional<ImageData> dbImageData = imageDataRepository.findByImageName(name.getBytes());
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
