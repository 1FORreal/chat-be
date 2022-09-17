package com.example.kalista.models.dtos;

import com.example.kalista.models.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class MessageDto {
    private Integer id;
    private String content;

    @JsonIgnoreProperties(value = "messages")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
