package com.example.kalista.controllers;

import com.example.kalista.models.dtos.MessageDto;
import com.example.kalista.models.dtos.UserDto;
import com.example.kalista.models.entities.Message;
import com.example.kalista.models.entities.User;
import com.example.kalista.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(
            UserService userService,
            ModelMapper modelMapper
    ) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<HttpStatus> findAllUsers() {
        List<User> users = this.userService.findAllUsers();
        List<UserDto> userDtos = new ArrayList();

        for(User user : users)
            userDtos.add(this.modelMapper.map(user, UserDto.class));

        return new ResponseEntity(userDtos, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<HttpStatus> findUserById(@PathVariable(name = "user_id") String userId) {
        User user = this.userService.findUserById(userId);
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        return new ResponseEntity(userDto, HttpStatus.OK);
    }

    @GetMapping("/{user_id}/messages")
    public ResponseEntity<HttpStatus> findAllMessagesByUser(
            @PathVariable(name = "user_id") String userId
    ) {
        List<Message> messages = this.userService.findAllMessagesByUser(userId);
        List<MessageDto> messageDtos = new ArrayList();

        for(Message message : messages)
            messageDtos.add(this.modelMapper.map(message, MessageDto.class));

        return new ResponseEntity(messageDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);

        this.userService.createUser(user);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/{user_id}/messages")
    public ResponseEntity<HttpStatus> createMessageByUser(
            @PathVariable(name = "user_id") String userId,
            @RequestBody MessageDto messageDto
    ) {
        Message message = this.modelMapper.map(messageDto, Message.class);

        this.userService.createMessageByUser(userId, message);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);

        this.userService.updateUser(user);

        return new ResponseEntity(HttpStatus.OK);
    }


    @DeleteMapping("/{user_id}")
    public ResponseEntity<HttpStatus> removeUserById(@PathVariable(name = "user_id") String userId) {
        this.userService.removeUserById(userId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}/messages/{message_id}")
    public ResponseEntity<HttpStatus> removeMessageByUser(
            @PathVariable(name = "user_id") String userId,
            @PathVariable(name = "message_id") Integer messageId
    ) {
        this.userService.removeMessageByUser(userId, messageId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
