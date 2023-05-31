package com.example.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
@CrossOrigin("*")

@RequiredArgsConstructor
public class ImageController {

    private final ResourceLoader resourceLoader;

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws MalformedURLException {
        Path imageFilePath = Paths.get("images/" + imageName);
        Resource resource = new UrlResource(imageFilePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
