package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.repository.ArtistaRepository;
import com.example.soundspherebackend.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    public ArtistaRepository artistaRepository;

    @Autowired
    public ArtistaService artistaService;

    @GetMapping("/fav/{idUsuario}")
    public List<ArtistaDTO> artistasFav(@PathVariable Integer idUsuario) {
        return artistaService.artistasFav(idUsuario);
    }

}
