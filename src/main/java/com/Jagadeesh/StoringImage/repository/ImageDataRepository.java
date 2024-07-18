package com.Jagadeesh.StoringImage.repository;

import com.Jagadeesh.StoringImage.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData,Long> {



    Optional<ImageData> findByImageName(byte[] imageName);
}
