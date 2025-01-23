package com.example.radiant.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.radiant.Models.Empresa;
import com.example.radiant.Service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/todas")
    public List<Empresa> getAllEmpresas() {
        return empresaService.obtenerEmpresas();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Empresa> registrarEmpresa(@RequestBody Empresa empresa) {
        Empresa nuevaEmpresa = empresaService.registrarEmpresa(empresa);
        return ResponseEntity.ok(nuevaEmpresa);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.obtenerEmpresaPorId(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        Empresa empresaActualizada = empresaService.actualizarEmpresa(id, empresa);
        return ResponseEntity.ok(empresaActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{empresaId}/documento")
    public ResponseEntity<Empresa> subirDocumento(@PathVariable Long empresaId,
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            Empresa empresaActualizada = empresaService.subirDocumentoVerificacion(empresaId, archivo);
            return ResponseEntity.ok(empresaActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{empresaId}/documento")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Long empresaId) {
        try {
            empresaService.eliminarDocumentoVerificacion(empresaId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
