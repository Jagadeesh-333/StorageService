package com.Jagadeesh.StoringImage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Image_Data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    //    @Column(name = "image_data", length = 255)
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData; // Corrected field name

}
