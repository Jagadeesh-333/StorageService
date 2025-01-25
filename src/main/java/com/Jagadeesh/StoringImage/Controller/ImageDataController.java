package com.Jagadeesh.StoringImage.Controller;

import com.Jagadeesh.StoringImage.repository.ImageDataRepository;
import com.Jagadeesh.StoringImage.service.ImageDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin")
public class ImageDataController {

    @Autowired
    private ImageDataService imageDataService;
    @Autowired
    private ImageDataRepository imageDataRepository;

    @PostMapping(value = "/uploadImage", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload an image", description = "Upload an image to the server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image uploaded successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile multipartFile) {
        try {
            String uploadImage = imageDataService.uploadImage(multipartFile);
            return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Image upload failed: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping(value = "/downloadImage/{partFile}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Download an image", description = "Download an image from the server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image downloaded successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<byte[]> downloadImage(@PathVariable String partFile) {
        try {
            byte[] data = imageDataService.downloadImage(partFile);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
    @DeleteMapping(value = "/deleteImage/{partFile}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete an image", description = "Delete an image from the server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image deleted successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content(mediaType = "application/json"))
    })
    public String deleteImage(@PathVariable String partFile) {
        imageDataService.deleteImage(partFile);
        if (imageDataRepository.findByName(partFile).isPresent()) {
            return "Image deletion failed";
        }
        else {
            return "Image deleted successfully";
        }
    }
}
