package com.example.kalista.models.dtos;

import com.example.kalista.models.entities.Message;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class UserDto {
    private String id;
    private String username;
    private String password;

    @JsonIgnoreProperties(value = "user")
    private List<Message> messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
