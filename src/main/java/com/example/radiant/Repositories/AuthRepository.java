package com.example.radiant.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.radiant.Models.Usuario;

public interface AuthRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAll();

    Optional<Usuario> findByCorreo(String correo);
}
