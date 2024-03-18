package com.example.soundspherebackend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario_lista")
@Data
public class UsuarioLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_seguidor", nullable = false)
    private Usuario seguidor;

    @ManyToOne
    @JoinColumn(name = "id_lista", nullable = false)
    private Lista lista;
}

