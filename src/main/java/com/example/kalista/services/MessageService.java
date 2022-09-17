package com.example.kalista.services;

import com.example.kalista.models.entities.Message;
import com.example.kalista.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message findMessageById(Integer messageId) {
        return this.messageRepository.findById(messageId).get();
    }

    public void createMessage(Message message) {
        this.messageRepository.save(message);
    }

    public void updateMessage(Message message) {
        this.messageRepository.save(message);
    }

    public void removeMessageById(Integer messageId) {
        this.messageRepository.deleteById(messageId);
    }
}
