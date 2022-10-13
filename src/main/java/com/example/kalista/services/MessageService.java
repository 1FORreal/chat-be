package com.example.kalista.services;

import com.example.kalista.models.entities.Message;
import com.example.kalista.models.entities.User;
import com.example.kalista.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageService(
            MessageRepository messageRepository,
            UserService userService
    ) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public List<Message> findAllMessages() {
        return this.messageRepository.findAll();
    }

    public List<Message> findAllMessagesPaginated(
            Integer pageNumber,
            Integer pageSize
    ) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("creationDate").descending());

        Page<Message> pagedResult = messageRepository.findAll(paging);

        if(!pagedResult.hasContent()) return new ArrayList<Message>();

        return pagedResult.getContent();
    }

    public Message findMessageById(Integer messageId) {
        return this.messageRepository.findById(messageId).get();
    }

    public void createMessage(Message message) {
        User messageUser = message.getUser();
        User inSystemUser = this.userService.findUserById(messageUser.getId());

        message.setUser(inSystemUser);
        this.messageRepository.save(message);
    }

    public void updateMessage(Message message) {
        User messageUser = message.getUser();
        User inSystemUser = this.userService.findUserById(messageUser.getId());

        message.setUser(inSystemUser);
        this.messageRepository.save(message);
    }

    public void removeMessageById(Integer messageId) {
        this.messageRepository.deleteById(messageId);
    }

    public void removeAllMessages() {
        this.messageRepository.deleteAll();
    }
}
