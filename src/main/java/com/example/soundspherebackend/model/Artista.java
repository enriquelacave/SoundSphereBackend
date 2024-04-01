package com.example.soundspherebackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "artista")
@Data
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String biografia;

    @Column(name = "url_imagen")
    private String urlImagen;

    @ManyToMany(mappedBy = "artistas")
    private Set<Album> albumes;

    @ManyToMany(mappedBy = "artistas")
    private Set<Cancion> canciones;

    @ManyToMany
    @JoinTable(
            name = "evento_artista",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_artista")
    )
    private Set<Evento> eventos;

    @ManyToMany(mappedBy = "artistas")
    private Set<Genero> generos;

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, biografia, urlImagen);
    }
}

