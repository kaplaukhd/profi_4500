package com.example.myproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class FileUploadService {

    public void store(MultipartFile file, String time) {
        if (!file.isEmpty()) {
            try {
                Path path = Paths.get("src/main/resources/static/img/" + time + file.getOriginalFilename());
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(Files.createFile(path).toFile()));
                stream.write(bytes);
                stream.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
