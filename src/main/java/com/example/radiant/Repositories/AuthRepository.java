package com.example.radiant.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.User;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
