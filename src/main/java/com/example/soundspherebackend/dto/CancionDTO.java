package com.example.soundspherebackend.dto;

import lombok.Data;
import java.util.Set;

@Data
public class CancionDTO {
    private Integer id;
    private String titulo;
    private Integer duracion;
    private String url;
    private Integer idAlbum; // ID del álbum al que pertenece la canción
    private Set<Integer> idArtistas; // Lista de IDs de los artistas asociados a la canción
    private Set<Integer> idGeneros; // Lista de IDs de los géneros asociados a la canción
}
