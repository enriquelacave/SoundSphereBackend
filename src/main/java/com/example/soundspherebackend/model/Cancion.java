package com.example.soundspherebackend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cancion")
@Data
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer duracion;

    private String url;

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;

    @ManyToMany
    @JoinTable(
            name = "cancion_artista",
            joinColumns = @JoinColumn(name = "id_cancion"),
            inverseJoinColumns = @JoinColumn(name = "id_artista")
    )
    private Set<Artista> artistas;

    @ManyToMany(mappedBy = "canciones")
    private Set<Genero> generos;

    @ManyToMany
    @JoinTable(
            name = ("lista_cancion"),
            joinColumns = @JoinColumn(name = "id_cancion"),
            inverseJoinColumns = @JoinColumn(name = "id_lista")
    )
    private List<Lista> listas;

    @OneToMany(mappedBy = "cancion", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Reproduccion> reproducciones;



}
