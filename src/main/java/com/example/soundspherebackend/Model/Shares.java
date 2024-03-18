package com.example.soundspherebackend.Model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shares")
@Data
public class Shares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_emisor", nullable = false)
    private Usuario emisor;

    @ManyToOne
    @JoinColumn(name = "id_receptor", nullable = false)
    private Usuario receptor;

    @Column(nullable = false)
    private Integer tipo;

    @Column(name = "id_elemento", nullable = false)
    private Long idElemento;

    @Column(nullable = false)
    private Date fecha;
}
