package com.example.radiant.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Categoria;
import com.example.radiant.Repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public void borrarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        categoriaRepository.delete(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaActual = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoriaActual.setNombre(categoria.getNombre());
        categoriaActual.setDescripcion(categoria.getDescripcion());
        return categoriaRepository.save(categoriaActual);
    }
}
