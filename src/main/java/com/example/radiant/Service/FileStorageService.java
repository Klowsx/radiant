package com.example.radiant.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.radiant.Config.FileStorageConfig;

@Service
public class FileStorageService {

    @Autowired
    private FileStorageConfig fileStorageConfig;

    public String saveFile(MultipartFile file, String fileType) throws Exception {

        Path uploadPath = fileStorageConfig.getUploadPath(fileType);

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path destination = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        return destination.toString();
    }

    public void deleteFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);

        Files.deleteIfExists(path);
    }
}
