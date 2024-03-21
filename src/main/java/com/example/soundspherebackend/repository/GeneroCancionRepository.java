package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.GeneroCancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroCancionRepository extends JpaRepository<GeneroCancion, Integer> {
}
