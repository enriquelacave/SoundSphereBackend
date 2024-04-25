package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {
    List<Cancion> findByAlbumId(Integer idAlbum);
}
