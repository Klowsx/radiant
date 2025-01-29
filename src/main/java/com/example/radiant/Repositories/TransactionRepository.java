package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
