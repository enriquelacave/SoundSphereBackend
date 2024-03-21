package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Tokens, Integer> {
}
