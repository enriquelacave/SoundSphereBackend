package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.ListaCancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaCancionRepository extends JpaRepository<ListaCancion, Integer> {
}
