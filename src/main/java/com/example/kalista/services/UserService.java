package com.example.kalista.services;

import com.example.kalista.models.entities.User;
import com.example.kalista.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User findUserById(String userId) {
        return this.userRepository.findById(userId).get();
    }

    public void createUser(User user) {
        this.userRepository.save(user);
    }

    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    public void removeUserById(String userId) {
        this.userRepository.deleteById(userId);
    }
}
