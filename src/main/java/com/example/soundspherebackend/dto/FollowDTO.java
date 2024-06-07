package com.example.soundspherebackend.dto;

import lombok.Data;

@Data
public class FollowDTO {
    private Integer id;
    private Integer seguidorId; // ID del usuario seguidor
    private Integer seguidoId; // ID del usuario seguido
}
