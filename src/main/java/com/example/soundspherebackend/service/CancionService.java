package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.model.Cancion;
import com.example.soundspherebackend.repository.AlbumRepository;
import com.example.soundspherebackend.repository.ArtistaRepository;
import com.example.soundspherebackend.repository.CancionRepository;
import com.example.soundspherebackend.repository.ReproduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private ReproduccionRepository reproduccionRepository;

    public List<Cancion> listarAlbumes() {
        return cancionRepository.findAll();
    }

    /**
     * Crear un álbum
     */
    public String crearCancion(CancionDTO cancionDTO) {
        Cancion nuevaCancion = new Cancion();
        nuevaCancion.setTitulo(cancionDTO.getTitulo());
        nuevaCancion.setDuracion(cancionDTO.getDuracion());
        nuevaCancion.setUrl(cancionDTO.getUrl());

        Album album = albumRepository.findById(cancionDTO.getIdAlbum()).orElse(null);

        if (album == null) {
            return "No se ha creado la canción, no se ha encontrado el album";
        } else {
            nuevaCancion.setAlbum(album);
            cancionRepository.save(nuevaCancion);
            return "Se ha creado el registro correctamente";
        }
    }

    public List<CancionDTO> getSongsByIdAlbum(Integer idAlbum) {
        List<CancionDTO> canciones = new ArrayList<>();
        List<Cancion> cancionesEntidad = cancionRepository.findByAlbumId(idAlbum);
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        for (Cancion cancion : cancionesEntidad) {
            CancionDTO cancionDTO = new CancionDTO();
            cancionDTO.setId(cancion.getId());
            cancionDTO.setTitulo(cancion.getTitulo());
            cancionDTO.setDuracion(cancion.getDuracion());
            cancionDTO.setUrl(cancion.getUrl());
            cancionDTO.setArtistas(getArtistBySong(cancion.getId()));
            if (albumOptional.isPresent()) {
                Album album = albumOptional.get();
                cancionDTO.setIdAlbum(album.getId());
                cancionDTO.setUrlImagenAlbum(album.getUrlImagen());}

            canciones.add(cancionDTO);
        }
        return canciones;
    }

    public List<ArtistaDTO> getArtistBySong(Integer idCancion) {
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

    public List<CancionDTO> getSongsByArtist(Integer idArtista) {
        Optional<Artista> artistaOptional = artistaRepository.findById(idArtista);
        List<CancionDTO> cancionesDTO = new ArrayList<>();

        if (artistaOptional.isPresent()) {
            Artista artista = artistaOptional.get();
            Set<Cancion> canciones = artista.getCanciones();
            for (Cancion cancion : canciones) {
                CancionDTO cancionDTO = new CancionDTO();
                cancionDTO.setId(cancion.getId());
                cancionDTO.setTitulo(cancion.getTitulo());
                cancionDTO.setUrlImagenAlbum(cancion.getAlbum().getUrlImagen());
                cancionDTO.setDuracion(cancion.getDuracion());
                cancionDTO.setReproducciones(getReproduccionesTotales(cancion.getId()));
                cancionDTO.setArtistas(getArtistBySong(cancion.getId()));

                cancionesDTO.add(cancionDTO);
            }
        }
        return cancionesDTO;
    }

    public Integer getReproduccionesTotales(Integer cancionId) {
        return reproduccionRepository.countByCancionId(cancionId);
    }
}
