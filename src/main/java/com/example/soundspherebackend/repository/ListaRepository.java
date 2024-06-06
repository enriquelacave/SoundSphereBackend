package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista, Integer> {

    List<Lista> findByUsuarioId(Integer userId);
}
