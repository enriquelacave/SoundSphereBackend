package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {


    @Query(value = "SELECT a.id " +
            "FROM artista a " +
            "JOIN cancion_artista ca ON ca.id_artista = a.id " +
            "JOIN cancion c ON ca.id_cancion = c.id " +
            "JOIN reproduccion r ON r.id_cancion = c.id " +
            "JOIN usuario u ON r.id_usuario = u.id " +
            "JOIN login l ON u.id_login = l.id " +
            "WHERE l.id = :idUsuario " +
            "GROUP BY a.id " +
            "ORDER BY COUNT(r.id) DESC " +
            "LIMIT 4", nativeQuery = true)
    List<Integer> artistasfavs(@Param("idUsuario") Integer idUsuario);

    List<Artista> findByNombreContainingIgnoreCase(String query);

}
