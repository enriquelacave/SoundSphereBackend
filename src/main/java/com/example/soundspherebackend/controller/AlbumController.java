package com.example.soundspherebackend.controller;


import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.CreateAlbumRequestDTO;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Integer albumId) {
        AlbumDTO albumDTO = albumService.getAlbumById(albumId);
        if (albumDTO != null) {
            return ResponseEntity.ok(albumDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody CreateAlbumRequestDTO requestDTO) {
        // Extraer el objeto AlbumDTO y la lista de IDs de artistas del cuerpo de la solicitud
        AlbumDTO albumDTO = requestDTO.getAlbumDTO();
        List<Integer> idArtistas = requestDTO.getIdArtistas();

        // Llamar al método createAlbum del servicio AlbumService
        AlbumDTO createdAlbum = albumService.createAlbum(albumDTO, idArtistas);

        // Devolver la respuesta con el objeto AlbumDTO creado y el código de estado correspondiente
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }


    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Integer albumId, @RequestBody AlbumDTO albumDTO) {
        AlbumDTO updatedAlbum = albumService.updateAlbum(albumId, albumDTO);
        if (updatedAlbum != null) {
            return ResponseEntity.ok(updatedAlbum);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer albumId) {
        albumService.deleteAlbum(albumId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/latest")
    public ResponseEntity<List<AlbumDTO>> getLast20Albums() {
        List<AlbumDTO> albums = albumService.getLast20Albums();
        return ResponseEntity.ok(albums);
    }
}