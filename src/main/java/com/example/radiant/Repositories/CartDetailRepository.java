package com.example.radiant.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    List<CartDetail> findByCartId(Long cartId);
}
