package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.CancionArtista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionArtistaRepository extends JpaRepository<CancionArtista, Integer> {
}
