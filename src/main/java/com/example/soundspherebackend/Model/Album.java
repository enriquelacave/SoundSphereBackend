package com.example.soundspherebackend.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
@Entity
@Table(name = "album")
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "url_imagen")
    private String urlImagen;

    @Column(name = "fecha_publicacion", nullable = false)
    private Date fechaPublicacion;
}

