package com.example.radiant.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.radiant.Models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
