package com.controller;

package com.controller;

import com.model.Image;
import com.repository.ImageRepository;
import com.utility.FileUploadUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileUploadUtility fileUploadUtility;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded image to a directory or cloud storage
            String imageUrl = fileUploadUtility.uploadFile(file);

            // Create an Image object and save it to the database
            Image image = new Image();
            image.setImageUrl(imageUrl);
            imageRepository.save(image);

            return "Image uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Image upload failed.";
        }
    }

    @GetMapping("/list")
    public List<Image> listImages() {
        // Retrieve a list of all images from the database
        return imageRepository.findAll();
    }
}

