package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    List<Album> findByTituloContainingIgnoreCase(String query);

    @Query(value = "SELECT a.id AS album_id, a.titulo AS album_titulo, a.url_imagen AS album_url_imagen, " +
            "a.fecha_publicacion AS album_fecha_publicacion, " +
            "ar.id AS artista_id, ar.nombre AS artista_nombre " +
            "FROM album a " +
            "INNER JOIN album_artista aa ON a.id = aa.id_album " +
            "INNER JOIN artista ar ON aa.id_artista = ar.id " +
            "ORDER BY a.fecha_publicacion DESC " +
            "LIMIT 8 ", nativeQuery = true)
    List<Object[]> findLast20AlbumsWithArtists();


}
