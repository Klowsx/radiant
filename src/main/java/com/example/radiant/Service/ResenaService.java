package com.example.radiant.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Resena;
import com.example.radiant.Repositories.ResenaRepository;

@Service
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    public Resena agregarResena(Resena resena) {
        resena.setCreadoEn(LocalDateTime.now());
        return resenaRepository.save(resena);
    }

    public Resena actualizarResena(Long id, Resena resenaActualizada) {
        Resena resenaExistente = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        resenaExistente.setCalificacion(resenaActualizada.getCalificacion());
        resenaExistente.setComentario(resenaActualizada.getComentario());
        resenaExistente.setActualizadoEn(LocalDateTime.now());

        return resenaRepository.save(resenaExistente);
    }

    public void eliminarResena(Long id) {
        Resena resena = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
        resenaRepository.delete(resena);
    }

    public Optional<Resena> obtenerResenaPorId(Long id) {
        return resenaRepository.findById(id);
    }

}
