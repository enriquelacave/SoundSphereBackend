package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.ListaCancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaCancionRepository extends JpaRepository<ListaCancion, Integer> {
    List<ListaCancion> findByListaId(Integer listaId);

    void deleteByListaId(Integer listaId);

    void deleteByListaIdAndCancionId(Integer listaId, Integer cancionId);
}
