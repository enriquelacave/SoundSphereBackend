package com.example.soundspherebackend.service;

import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {


    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginRepository.findTopByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
