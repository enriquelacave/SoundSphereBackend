package com.example.soundspherebackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TokensDTO {
    private Integer id;
    private String tokenUsuario;
    private Date fechaExpiracion;
    private Integer loginId;
}
