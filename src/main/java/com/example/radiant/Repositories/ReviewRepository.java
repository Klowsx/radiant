package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}