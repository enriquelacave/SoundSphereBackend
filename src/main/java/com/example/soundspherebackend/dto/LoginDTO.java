package com.example.soundspherebackend.dto;

import com.example.soundspherebackend.model.Enum.Rol;
import lombok.Data;

@Data
public class LoginDTO {
    private Integer id;
    private String username;
    private String password;
    private Rol rol;
}
