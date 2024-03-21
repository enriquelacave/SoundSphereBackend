package com.example.soundspherebackend.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "genero_cancion")
@Data
public class GeneroCancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "id_cancion", nullable = false)
    private Cancion cancion;
}

