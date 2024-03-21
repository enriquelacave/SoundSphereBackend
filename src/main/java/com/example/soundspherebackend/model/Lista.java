package com.example.soundspherebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "lista")
@Data
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToMany(mappedBy = "listasSeguidas")
    private Set<Usuario> seguidores;

    @ManyToMany(mappedBy = "listas")
    private List<Cancion> canciones;
}

