package com.example.soundspherebackend.dto;

import lombok.Data;

@Data
public class CancionArtistaDTO {
    private Integer id;
    private Integer idCancion; // ID de la canción asociada
    private Integer idArtista; // ID del artista asociado
}
