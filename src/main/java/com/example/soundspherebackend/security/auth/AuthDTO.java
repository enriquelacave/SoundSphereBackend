package com.example.soundspherebackend.security.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDTO {

    private String token;
    private String rol;
    private Integer idLogin;
    private String info;
    private Integer idUser;
}
