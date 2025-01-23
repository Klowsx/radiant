package com.example.radiant.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.radiant.Models.Empresa;
import com.example.radiant.Repositories.EmpresaRepository;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Empresa registrarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> obtenerEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> obtenerEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa actualizarEmpresa(Long id, Empresa empresa) {
        Empresa empresaActual = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        empresaActual.setNombre_empresa(empresa.getNombre_empresa());
        empresaActual.setDireccion(empresa.getDireccion());
        empresaActual.setTelefono(empresa.getTelefono());
        empresaActual.setCorreo(empresa.getCorreo());
        return empresaRepository.save(empresaActual);
    }

    public void eliminarEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al eliminar, empresa no encontrada"));

        empresaRepository.delete(empresa);
    }

    private static final List<String> formatoArchivo = Arrays.asList("application/pdf");

    public Empresa subirDocumentoVerificacion(Long empresaId, MultipartFile archivo) throws Exception {
        if (formatoArchivo.contains(archivo.getContentType())) {
            Empresa empresa = empresaRepository.findById(empresaId)
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

            String rutaArchivo = fileStorageService.guardarArchivo(archivo, "companies");

            empresa.setDocumento_verificacion(rutaArchivo);

            return empresaRepository.save(empresa);
        } else {
            throw new Exception("Formato no permitido. El formato permitido es .PDF");
        }
    }

    public Empresa eliminarDocumentoVerificacion(Long empresaId) throws Exception {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        String rutaArchivo = empresa.getDocumento_verificacion();
        if (rutaArchivo != null) {
            fileStorageService.eliminarArchivo(rutaArchivo);
        } else {
            throw new Exception("No hay un archivo para eliminar");
        }

        empresa.setDocumento_verificacion(null);
        return empresaRepository.save(empresa);
    }

}
