package com.example.radiant.Service;

import org.springframework.stereotype.Service;

import com.example.radiant.Models.Message;
import com.example.radiant.Repositories.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
