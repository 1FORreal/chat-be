package com.example.kalista.models.dtos;

import com.example.kalista.models.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

public class MessageDto {
    private Integer id;
    private String content;
    private Date creationDate;

    @JsonIgnoreProperties({"messages", "password"})
    private UserDto user;

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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
