package com.example.soundspherebackend.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "genero")
@Data
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
}
