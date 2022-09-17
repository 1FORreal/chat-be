package com.example.kalista.controllers;

import com.example.kalista.models.dtos.MessageDto;
import com.example.kalista.models.entities.Message;
import com.example.kalista.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final ModelMapper modelMapper;

    @Autowired
    public MessageController(
            MessageService messageService,
            ModelMapper modelMapper
    ) {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    /*@GetMapping
    public ResponseEntity<HttpStatus> findAllMessages() {
        List<Message> messages = this.messageService.findAllMessages();
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages)
            messageDtos.add(this.modelMapper.map(message, MessageDto.class));

        return new ResponseEntity(messageDtos, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<HttpStatus> findAllMessagesPaginated(
            @RequestParam(name = "page_number") Integer pageNumber,
            @RequestParam(name = "page_size") Integer pageSize
    ) {
        List<Message> messages = this.messageService.findAllMessagesPaginated(pageNumber, pageSize);
        List<MessageDto> messageDtos = new ArrayList();

        for(Message message : messages)
            messageDtos.add(this.modelMapper.map(message,MessageDto.class));

        return new ResponseEntity(messageDtos, HttpStatus.OK);
    }

    @GetMapping("/{message_id}")
    public ResponseEntity<HttpStatus> findMessageById(@PathVariable(name = "message_id") Integer messageId) {
        Message message = this.messageService.findMessageById(messageId);
        MessageDto messageDto = this.modelMapper.map(message, MessageDto.class);

        return new ResponseEntity(messageDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createMessage(@RequestBody MessageDto messageDto) {
        Message message = this.modelMapper.map(messageDto, Message.class);

        this.messageService.createMessage(message);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateMessage(@RequestBody MessageDto messageDto) {
        Message message = this.modelMapper.map(messageDto, Message.class);

        this.messageService.updateMessage(message);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{message_id}")
    public ResponseEntity<HttpStatus> removeMessageById(@PathVariable(name = "message_id") Integer messageId) {
        this.messageService.removeMessageById(messageId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> removeAllMessages(){
        this.messageService.removeAllMessages();

        return new ResponseEntity(HttpStatus.OK);
    }
}
