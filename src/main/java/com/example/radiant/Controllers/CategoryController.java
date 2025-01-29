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

import com.example.radiant.Models.Category;
import com.example.radiant.Service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoriaService;

    @GetMapping("/all")
    public List<Category> obtenerCategorias() {
        return categoriaService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> obtenerCategoraiPorId(@PathVariable Long id) {
        Optional<Category> categoria = categoriaService.findCategoryById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Category> crearCategoria(@RequestBody Category categoria) {
        Category categoriaCreada = categoriaService.addCategory(categoria);
        return ResponseEntity.ok(categoriaCreada);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> actualizarCategoria(@PathVariable Long id, Category categoria) {
        Category categoriaActualizada = categoriaService.updateCategory(id, categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.deteleCategory(id);
        return ResponseEntity.ok().body(null);
    }
}
