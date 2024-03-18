package com.example.soundspherebackend.Model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "evento_artista")
@Data
public class EventoArtista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;
}

