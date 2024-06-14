package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.repository.AlbumRepository;
import com.example.soundspherebackend.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cancion")
public class CancionController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CancionService cancionService;

    @GetMapping("/album/{albumId}")
    public List<CancionDTO> getSongsByIdAlbum(@PathVariable Integer albumId) {
        return cancionService.getSongsByIdAlbum(albumId);
    }

    @GetMapping("/artista/{artistaId}")
    public List<CancionDTO> getSongsByIdArtist(@PathVariable Integer artistaId) {
        return cancionService.getSongsByArtist(artistaId);
    }


}
