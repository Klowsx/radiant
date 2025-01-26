package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.DetallePedido;

@Repository
public interface DetalleRepository extends JpaRepository<DetallePedido, Long> {

}
