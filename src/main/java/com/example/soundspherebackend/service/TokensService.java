package com.example.soundspherebackend.service;

import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.model.Tokens;
import com.example.soundspherebackend.repository.TokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokensService {

    @Autowired
    private TokensRepository tokensRepository;

    public Tokens save(Tokens tokens){
        return tokensRepository.save(tokens);
    }
}
