package com.example.soundspherebackend.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "album_artista")
@Data
public class AlbumArtista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;
}

