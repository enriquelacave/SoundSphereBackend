package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.model.Cancion;
import com.example.soundspherebackend.repository.ArtistaRepository;
import com.example.soundspherebackend.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/{idArtista}")
    public ArtistaDTO getData(@PathVariable Integer idArtista) {
        Optional<Artista> artistaOptional = artistaRepository.findById(idArtista);
        ArtistaDTO artistaDTO = new ArtistaDTO();
        if (artistaOptional.isPresent()) {
            Artista artista = artistaOptional.get();

            artistaDTO.setId(artista.getId());
            artistaDTO.setNombre(artista.getNombre());
            artistaDTO.setUrlImagen(artista.getUrlImagen());
            artistaDTO.setBiografia(artista.getBiografia());
        }
        return artistaDTO;
    }

    @GetMapping("/album/{idArtista}")
    public List<AlbumDTO> getAlbumsbyArtist(@PathVariable Integer idArtista) {
        Optional<Artista> artistaOptional = artistaRepository.findById(idArtista);
        List<AlbumDTO> albumesDTO = new ArrayList<>();
        if (artistaOptional.isPresent()) {
            Artista artista = artistaOptional.get();
            Set<Album> albums = artista.getAlbumes();
            for (Album album : albums) {
                AlbumDTO albumDTO = new AlbumDTO();
                albumDTO.setId(album.getId());
                albumDTO.setTitulo(album.getTitulo());
                albumDTO.setUrlImagen(album.getUrlImagen());
                albumDTO.setFechaPublicacion(album.getFechaPublicacion());
                albumesDTO.add(albumDTO);
            }
        }
        return albumesDTO;
    }
}
