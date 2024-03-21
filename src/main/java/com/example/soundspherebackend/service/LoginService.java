package com.example.soundspherebackend.service;

import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService{
    @Autowired
    private LoginRepository loginRepository;

}
