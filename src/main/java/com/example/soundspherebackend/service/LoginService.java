package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.LoginDTO;
import com.example.soundspherebackend.mapper.LoginMapper;
import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Login loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginRepository.findTopByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public Login save(LoginDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return loginRepository.save(loginMapper.toEntity(dto));
    }

    public Login buscarPorUsername(String username) {
        return loginRepository.findTopByUsername(username).orElse(null);
    }
}
