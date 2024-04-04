package com.example.soundspherebackend.mapper;

import com.example.soundspherebackend.dto.LoginDTO;
import com.example.soundspherebackend.model.Login;
import org.springframework.stereotype.Component;


@Component
public class LoginMapper {
    public LoginDTO toDTO(Login login) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(login.getId());
        loginDTO.setUsername(login.getUsername());
        loginDTO.setPassword(login.getPassword());
        loginDTO.setRol(login.getRol());
        return loginDTO;
    }

    public Login toEntity(LoginDTO loginDTO) {
        Login login = new Login();
        login.setId(loginDTO.getId());
        login.setUsername(loginDTO.getUsername());
        login.setPassword(loginDTO.getPassword());
        login.setRol(loginDTO.getRol());
        return login;
    }
}
