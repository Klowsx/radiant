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

import com.example.radiant.Models.Review;
import com.example.radiant.Service.ReviewService;

@RestController
@RequestMapping("/resena")
public class ReviewController {
    @Autowired
    private ReviewService resenaService;

    @PostMapping("/agregar/")
    public ResponseEntity<Review> agregarResena(@RequestBody Review resena) {
        Review nuevaResena = resenaService.addReview(resena);
        return ResponseEntity.ok(nuevaResena);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Review> actualizarResena(@PathVariable Long id, @RequestBody Review resena) {
        Review resenaActualizada = resenaService.updateReview(id, resena);
        return ResponseEntity.ok(resenaActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarResena(@PathVariable Long id) {
        resenaService.deleteReview(id);
        return ResponseEntity.ok("Reseña eliminada correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> obtenerResena(@PathVariable Long id) {
        Optional<Review> resena = resenaService.getReviewById(id);
        return resena.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
