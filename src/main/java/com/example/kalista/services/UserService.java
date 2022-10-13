package com.example.kalista.services;

import com.example.kalista.models.entities.Message;
import com.example.kalista.models.entities.User;
import com.example.kalista.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
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

    public User authenticateUser(String username, String password) {
        Optional<User> optionalUser = this.userRepository.findUserByUsername(username);

        if(!optionalUser.isPresent()) throw new RuntimeException("Incorrect credentials!");
        if(!optionalUser.get().getPassword().equals(password)) throw new RuntimeException("Incorrect credentials");

        return optionalUser.get();
    }

    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    public void removeUserById(String userId) {
        this.userRepository.deleteById(userId);
    }

    public boolean userExistsById(String userId) {
        User user = this.findUserById(userId);

        return (user != null ? true : false);
    }
}
