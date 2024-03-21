package com.example.soundspherebackend.dto;

import lombok.Data;

@Data
public class EventoArtistaDTO {
    private Integer id;
    private Integer idEvento; // ID del evento asociado
    private Integer idArtista; // ID del artista asociado
}
