package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
