package com.example.soundspherebackend.Model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "follow")
@Data
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_seguidor", nullable = false)
    private Usuario seguidor;

    @ManyToOne
    @JoinColumn(name = "id_seguido", nullable = false)
    private Usuario seguido;
}

