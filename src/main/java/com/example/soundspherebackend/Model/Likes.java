package com.example.soundspherebackend.Model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "likes")
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_comentario", nullable = false)
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
