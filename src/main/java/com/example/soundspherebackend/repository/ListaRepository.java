package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista, Integer> {

    List<Lista> findByUsuarioId(Integer userId);

    @Query("SELECT COUNT(lc) > 0 FROM ListaCancion lc WHERE lc.lista.id = :listaId AND lc.cancion.id = :cancionId")
    boolean existsByListaIdAndCancionId(@Param("listaId") Integer listaId, @Param("cancionId") Integer cancionId);
}
