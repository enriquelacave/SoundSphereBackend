package com.example.soundspherebackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class AlbumDTO {

    @NotNull
    private Integer id;

    private String titulo;

    private String urlImagen;

    private LocalDate fechaPublicacion;

    private List<ArtistaDTO> artistas;

    private List<CancionDTO> canciones;

    private List<ComentarioDTO> comentarios;

}
