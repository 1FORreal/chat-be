package com.example.kalista.repositories;

import com.example.kalista.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findUserByUsername(String username);
}
