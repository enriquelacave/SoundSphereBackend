package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
