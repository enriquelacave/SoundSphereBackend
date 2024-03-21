package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
