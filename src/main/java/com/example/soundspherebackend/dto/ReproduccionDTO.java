package com.example.soundspherebackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReproduccionDTO {
    private Integer id;
    private Date fechaPublicacion;
    private Integer usuarioId;
    private Integer cancionId;
}
