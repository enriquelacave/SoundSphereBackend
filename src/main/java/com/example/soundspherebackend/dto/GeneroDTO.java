package com.example.soundspherebackend.dto;

import lombok.Data;
import java.util.List;

@Data
public class GeneroDTO {
    private Integer id;
    private String nombre;
    private List<Integer> idArtistas; // Lista de IDs de artistas asociados al género
    private List<Integer> idCanciones; // Lista de IDs de canciones asociadas al género
}
