package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.*;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.model.Cancion;
import com.example.soundspherebackend.model.Usuario;
import com.example.soundspherebackend.repository.CancionRepository;
import com.example.soundspherebackend.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private CancionRepository cancionRepository;

    @GetMapping
    public ResultadosDTO search(@RequestParam String q) {
        List<CancionDTO> songs = searchService.searchSongs(q).stream()
                .map(cancion -> {
                    CancionDTO dto = new CancionDTO();
                    dto.setId(cancion.getId());
                    dto.setTitulo(cancion.getTitulo());
                    dto.setIdAlbum(cancion.getAlbum().getId());
                    dto.setDuracion(cancion.getDuracion());
                    dto.setUrlImagenAlbum(cancion.getAlbum().getUrlImagen());
                    dto.setArtistas(getArtistBySong(cancion.getId()));
                    return dto;
                })
                .collect(Collectors.toList());
        List<ArtistaDTO> artists = searchService.searchArtists(q).stream()
                .map(artista -> {
                    ArtistaDTO dto = new ArtistaDTO();
                    dto.setId(artista.getId());
                    dto.setNombre(artista.getNombre());
                    dto.setUrlImagen(artista.getUrlImagen());
                    return dto;
                })
                .collect(Collectors.toList());
        List<AlbumDTO> albums = searchService.searchAlbums(q).stream()
                .map(album -> {
                    AlbumDTO dto = new AlbumDTO();
                    dto.setId(album.getId());
                    dto.setTitulo(album.getTitulo());
                    dto.setUrlImagen(album.getUrlImagen());
                    return dto;
                })
                .collect(Collectors.toList());
        List<UsuarioDTO> profiles = searchService.searchProfiles(q).stream()
                .map(usuario -> {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setId(usuario.getId());
                    dto.setNombre(usuario.getNombre());
                    dto.setApellidos(usuario.getApellidos());
                    dto.setUrlImagen(usuario.getUrlImagen());
                    return dto;
                })
                .collect(Collectors.toList());

        return new ResultadosDTO(songs, artists, albums, profiles);
    }

    private List<ArtistaDTO> getArtistBySong(Integer idCancion) {
        Optional<Cancion> cancionOptional = cancionRepository.findById(idCancion);
        List<ArtistaDTO> artistasDTO = new ArrayList<>();

        if (cancionOptional.isPresent()) {
            Cancion cancion = cancionOptional.get();
            Set<Artista> artistas = cancion.getArtistas();
            for (Artista artista : artistas) {
                ArtistaDTO artistaDTO = new ArtistaDTO();
                artistaDTO.setId(artista.getId());
                artistaDTO.setNombre(artista.getNombre());
                artistasDTO.add(artistaDTO);
            }
        }
        return artistasDTO;
    }
}
