package com.example.kalista.services;

import com.example.kalista.models.entities.Message;
import com.example.kalista.models.entities.User;
import com.example.kalista.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MessageService messageService;

    @Autowired
    public UserService(
            UserRepository userRepository,
            MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }


    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(String userId) {
        return this.userRepository.findById(userId).get();
    }

    public List<Message> findAllMessagesByUser(String userId) {
        User user = this.findUserById(userId);

        return user.getMessages();
    }

    public void createUser(User user) {
        this.userRepository.save(user);
    }

    public void createMessageByUser(String userId, Message message) {
        User user = this.findUserById(userId);

        List<Message> userMessages = user.getMessages();
        userMessages.add(message);
        user.setMessages(userMessages);

        message.setUser(user);

        this.messageService.createMessage(message);
    }

    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    public void removeUserById(String userId) {
        this.userRepository.deleteById(userId);
    }

    public void removeMessageByUser(String userId, Integer messageId) {
        Message message = this.messageService.findMessageById(messageId);

        if(!message.getUser().getId().equals(userId)) return;

        this.messageService.removeMessageById(messageId);
    }
}
