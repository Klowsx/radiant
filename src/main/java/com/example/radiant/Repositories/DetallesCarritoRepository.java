package com.example.radiant.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.radiant.Models.DetalleCarrito;

public interface DetallesCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
    List<DetalleCarrito> findByCarritoId(Long carritoId);
}
