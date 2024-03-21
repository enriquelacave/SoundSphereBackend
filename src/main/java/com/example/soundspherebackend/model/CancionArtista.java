package com.example.soundspherebackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cancion_artista")
@Data
public class CancionArtista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cancion", nullable = false)
    private Cancion cancion;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;
}
