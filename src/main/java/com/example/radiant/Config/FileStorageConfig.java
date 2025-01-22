package com.example.radiant.Config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStorageConfig {

    @Value("${file.upload-dir.products}")
    private String uploadDirProducts;

    @Value("${file.upload-dir.companies}")
    private String uploadDirCompanies;

    public Path getUploadPath(String tipoArchivo) throws Exception {
        Path path;

        if ("products".equals(tipoArchivo)) {
            path = Paths.get(uploadDirProducts).toAbsolutePath().normalize();
        } else if ("companies".equals(tipoArchivo)) {
            path = Paths.get(uploadDirCompanies).toAbsolutePath().normalize();
        } else {
            throw new Exception("Tipo de archivo no válido");
        }

        Files.createDirectories(path);
        return path;
    }
}
