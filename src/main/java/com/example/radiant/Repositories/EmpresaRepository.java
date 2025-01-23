package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.radiant.Models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
