package com.example.soundspherebackend.Model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "lista_cancion")
@Data
public class ListaCancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_lista", nullable = false)
    private Lista lista;

    @ManyToOne
    @JoinColumn(name = "id_cancion", nullable = false)
    private Cancion cancion;
}

