package com.example.soundspherebackend.model;

import com.example.soundspherebackend.model.Enum.Sexo;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;

    @OneToOne
    @JoinColumn(name = "id_login")
    private Login login;

    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "id_seguidor"),
            inverseJoinColumns = @JoinColumn(name = "id_seguido")
    )
    private Set<Usuario> seguidos;

    @ManyToMany(mappedBy = "seguidos")
    private Set<Usuario> seguidores;

    @OneToMany(mappedBy = "seguidor")
    private List<UsuarioLista> listasSeguidas;

    @OneToMany(mappedBy = "emisor", fetch = FetchType.LAZY)
    @Cascade (org.hibernate.annotations.CascadeType.ALL)
    private List<Shares> emitidos;

    @OneToMany(mappedBy = "receptor", fetch = FetchType.LAZY)
    @Cascade (org.hibernate.annotations.CascadeType.ALL)
    private List<Shares> recibidos;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Likes> likes;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Reproduccion> reproducciones;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Lista> listasCreadas;
}
