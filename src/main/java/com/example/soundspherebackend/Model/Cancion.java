package com.example.soundspherebackend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cancion")
@Data
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer duracion;

    private String url;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;
}
