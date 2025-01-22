package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.radiant.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
