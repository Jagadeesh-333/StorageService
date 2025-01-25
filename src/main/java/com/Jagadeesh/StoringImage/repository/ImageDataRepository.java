package com.Jagadeesh.StoringImage.repository;

import com.Jagadeesh.StoringImage.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String name); // New method to find image by name

    @Modifying
    @Transactional
    @Query("DELETE FROM ImageData i WHERE i.name = :name")
    void deleteByName(@Param("name")String partFile);

}
