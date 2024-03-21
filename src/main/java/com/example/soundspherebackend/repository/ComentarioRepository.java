package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
