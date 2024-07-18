package com.Jagadeesh.StoringImage.Controller;

import com.Jagadeesh.StoringImage.service.ImageDataService;
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

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile multipartFile) throws IOException {
                 String uploadImage= imageDataService.uploadImage(multipartFile);
                  return ResponseEntity.status(HttpStatus.OK)
                          .body(uploadImage);
    }
   @GetMapping("/downloadImage/{partFile}")  // partFile is the name of the file that you want to download from your server.  You need to replace it with your actual file name.  This is just a placeholder.  You would replace this with your actual file name.  For example, if you have a file named "myImage.jpg", then partFile would be "myImage.jpg".
   public ResponseEntity<?> downloadImage(@PathVariable String partFile){
        byte[] data=imageDataService.downloadImage(partFile);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(data);
   }

}
