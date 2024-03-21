package com.example.soundspherebackend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name = "tokens")
@Data
public class Tokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token_usuario", nullable = false)
    private String tokenUsuario;

    @Column(name = "fecha_expiracion", nullable = false)
    private Date fechaExpiracion;

    @ManyToOne
    @JoinColumn(name = "id_login")
    private Login login;
}

