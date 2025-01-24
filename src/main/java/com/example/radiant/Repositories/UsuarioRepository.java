package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.radiant.Models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
