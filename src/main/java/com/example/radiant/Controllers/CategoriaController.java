package com.example.radiant.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.radiant.Models.Categoria;
import com.example.radiant.Service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/todas")
    public List<Categoria> obtenerCategorias() {
        return categoriaService.obtenerCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoraiPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.buscarCategoriaPorId(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaCreada = categoriaService.crearCategoria(categoria);
        return ResponseEntity.ok(categoriaCreada);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, Categoria categoria) {
        Categoria categoriaActualizada = categoriaService.actualizarCategoria(id, categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.borrarCategoria(id);
        return ResponseEntity.ok().body(null);
    }
}
