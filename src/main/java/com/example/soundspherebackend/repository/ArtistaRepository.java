package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
}
