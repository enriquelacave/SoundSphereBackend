package com.example.soundspherebackend.dto;

import lombok.Data;

@Data
public class FollowDTO {
    private Integer id;
    private Integer idSeguidor; // ID del usuario seguidor
    private Integer idSeguido; // ID del usuario seguido
}
