package com.example.radiant.Service;

import com.example.radiant.Models.Message;
import com.example.radiant.Repositories.MessageRepository;

public class MessageService {
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
