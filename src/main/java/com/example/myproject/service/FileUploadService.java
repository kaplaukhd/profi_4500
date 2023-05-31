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
                String folderPath = "/app/images/";
                String fileName = time + file.getOriginalFilename();
                Path path = Paths.get(folderPath + fileName);
                byte[] bytes = file.getBytes();
                Files.createDirectories(path.getParent()); // Создать директорию, если ее нет
                Files.write(path, bytes); // Сохранить файл
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
