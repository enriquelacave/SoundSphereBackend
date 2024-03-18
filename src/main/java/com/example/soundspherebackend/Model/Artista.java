package com.example.soundspherebackend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "artista")
@Data
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String biografia;

    @Column(name = "url_imagen")
    private String urlImagen;
}

