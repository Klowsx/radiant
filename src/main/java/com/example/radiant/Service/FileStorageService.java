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

    public String guardarArchivo(MultipartFile archivo, String tipoArchivo) throws Exception {

        Path uploadPath = fileStorageConfig.getUploadPath(tipoArchivo);

        String archivoNombre = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();

        Path destino = uploadPath.resolve(archivoNombre);

        Files.copy(archivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        return destino.toString();
    }

    public void eliminarArchivo(String rutaArchivo) throws Exception {
        Path path = Paths.get(rutaArchivo);

        Files.deleteIfExists(path);
    }
}
