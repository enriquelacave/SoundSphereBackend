package com.example.soundspherebackend.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CancionDTO {
    private Integer id;
    private String titulo;
    private Integer duracion;
    private String url;
    private Integer idAlbum; // ID del álbum al que pertenece la canción
    private List<ArtistaDTO> artistas; // Lista de IDs de los artistas asociados a la canción
    private List<Integer> idGeneros; // Lista de IDs de los géneros asociados a la canción
    private String urlImagenAlbum;
    private List<Integer> idArtistas;
    private Integer reproducciones;
}