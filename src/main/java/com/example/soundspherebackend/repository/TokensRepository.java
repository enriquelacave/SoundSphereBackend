package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.model.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokensRepository extends JpaRepository<Tokens, Integer> {


    Tokens findTopByLogin(Login login);
}
