package com.example.soundspherebackend.model;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "comentario")
@Data
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String texto;

    @Column(name = "fecha_publicacion", nullable = false)
    private Date fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cancion", nullable = false)
    private Cancion cancion;

    @OneToMany(mappedBy = "comentario", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Likes> likes;
}

