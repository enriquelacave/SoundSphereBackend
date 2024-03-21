package com.example.soundspherebackend.dto;

import lombok.Data;
import java.util.Date;
import java.util.Set;

@Data
public class EventoDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private Set<Integer> idArtistas; // IDs de los artistas asociados al evento
}
