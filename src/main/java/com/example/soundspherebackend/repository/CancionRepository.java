package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {
}
