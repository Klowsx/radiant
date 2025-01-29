package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
