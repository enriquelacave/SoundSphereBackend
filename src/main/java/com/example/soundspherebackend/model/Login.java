package com.example.soundspherebackend.model;

import com.example.soundspherebackend.model.Enum.Rol;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "login")
@Data
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "rol")
    @Enumerated(EnumType.ORDINAL)
    private Rol rol;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
    private List<Tokens> tokens;

}
