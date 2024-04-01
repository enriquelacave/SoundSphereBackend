package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query(value = "SELECT a.id, a.titulo, a.url_imagen, a.fecha_publicacion, a2.id, a2.nombre AS nombre_artista " +
            "FROM album a " +
            "JOIN album_artista aa ON aa.id_album = a.id " +
            "JOIN artista a2 ON aa.id_artista = a2.id " +
            "ORDER BY a.fecha_publicacion DESC " +
            "LIMIT 20", nativeQuery = true)
    List<Object[]> findLast20Albums();

}
