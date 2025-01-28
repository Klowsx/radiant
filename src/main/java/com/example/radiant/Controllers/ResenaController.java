package com.example.radiant.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.radiant.Models.Resena;
import com.example.radiant.Service.ResenaService;

@RestController
@RequestMapping("/resena")
public class ResenaController {
    @Autowired
    private ResenaService resenaService;

    @PostMapping("/agregar/")
    public ResponseEntity<Resena> agregarResena(@RequestBody Resena resena) {
        Resena nuevaResena = resenaService.agregarResena(resena);
        return ResponseEntity.ok(nuevaResena);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Resena> actualizarResena(@PathVariable Long id, @RequestBody Resena resena) {
        Resena resenaActualizada = resenaService.actualizarResena(id, resena);
        return ResponseEntity.ok(resenaActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarResena(@PathVariable Long id) {
        resenaService.eliminarResena(id);
        return ResponseEntity.ok("Reseña eliminada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerResena(@PathVariable Long id) {
        Optional<Resena> resena = resenaService.obtenerResenaPorId(id);
        return resena.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
