package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

}
