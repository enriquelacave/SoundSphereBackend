package com.example.soundspherebackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "tokens")
@Data
@Builder
@EqualsAndHashCode(exclude = {"login"})
@RequiredArgsConstructor
public class Tokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token_usuario", nullable = false)
    private String tokenUsuario;

    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDateTime fechaExpiracion;

    @OneToOne
    @JoinColumn(name = "id_login")
    private Login login;

    public Tokens(Integer id, String tokenUsuario, LocalDateTime fechaExpiracion, Login login) {
        this.id = id;
        this.tokenUsuario = tokenUsuario;
        this.fechaExpiracion = fechaExpiracion;
    }
}
