package com.service;

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {

    // Define the directory where images will be stored (you can customize this)
    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        // Ensure the directory exists or create it
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate a unique file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

        // Save the file to the server
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath);

        // Save the image URL to the database
        String imageUrl = "/api/images/" + uniqueFileName;
        Image image = new Image();
        image.setImageUrl(imageUrl);
        imageRepository.save(image);

        return imageUrl;
    }

    public List<Image> getAllImages() {
        // Retrieve a list of all images from the database
        return imageRepository.findAll();
    }
}
