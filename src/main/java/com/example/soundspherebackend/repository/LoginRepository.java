package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {

    Optional<Login> findTopByUsername(String username);

    Boolean existsByUsernameAndPassword(String username, String password);
}
