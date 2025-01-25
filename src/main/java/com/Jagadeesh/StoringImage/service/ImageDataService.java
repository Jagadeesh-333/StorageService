package com.Jagadeesh.StoringImage.service;

import com.Jagadeesh.StoringImage.entity.ImageData;
import com.Jagadeesh.StoringImage.repository.ImageDataRepository;
import com.Jagadeesh.StoringImage.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        ImageData imageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(imageBytes))
                .build();

        try {
            imageDataRepository.save(imageData);
            return "Image uploaded successfully with name: " + file.getOriginalFilename();
        } catch (Exception e) {
            throw new IOException("Image upload failed: " + e.getMessage());
        }
    }

    public byte[] downloadImage(String name) throws IOException {
        Optional<ImageData> dbImageData = imageDataRepository.findByName(name);
        if (dbImageData.isPresent()) {
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        } else {
            throw new IOException("Image with name " + name + " not found.");
        }
    }

    @Transactional
    public void deleteImage(String partFile) {
        Optional<ImageData> dbImageData = imageDataRepository.findByName(partFile);
        if (dbImageData.isPresent()) {
            imageDataRepository.deleteByName(partFile);
        } else {
            throw new RuntimeException("Image with name " + partFile + " not found.");
        }
    }
}
