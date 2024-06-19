package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Reproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReproduccionRepository extends JpaRepository<Reproduccion, Integer> {

    @Query("SELECT COUNT(r) FROM Reproduccion r WHERE r.cancion.id = :cancionId")
    Integer countByCancionId(@Param("cancionId") Integer cancionId);

    @Query("SELECT r FROM Reproduccion r WHERE r.usuario.id = :idUsuario ORDER BY r.fechaPublicacion DESC")
    List<Reproduccion> findTop10DistinctByUsuarioIdOrderByFechaPublicacionDesc(Integer idUsuario);

}
