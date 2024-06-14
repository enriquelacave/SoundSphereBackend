package com.example.soundspherebackend.service;

import com.example.soundspherebackend.model.*;
import com.example.soundspherebackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final CancionRepository cancionRepository;
    private final ArtistaRepository artistaRepository;
    private final AlbumRepository albumRepository;
    private final UsuarioRepository usuarioRepository;
    private final ListaRepository listaRepository;

    @Autowired
    public SearchService(CancionRepository cancionRepository, ArtistaRepository artistaRepository,
                         AlbumRepository albumRepository, UsuarioRepository usuarioRepository,
                         ListaRepository listaRepository) {
        this.cancionRepository = cancionRepository;
        this.artistaRepository = artistaRepository;
        this.albumRepository = albumRepository;
        this.usuarioRepository = usuarioRepository;
        this.listaRepository = listaRepository;
    }

    public List<Cancion> searchSongs(String query) {
        return cancionRepository.findByTituloContainingIgnoreCase(query);
    }

    public List<Artista> searchArtists(String query) {
        return artistaRepository.findByNombreContainingIgnoreCase(query);
    }

    public List<Album> searchAlbums(String query) {
        return albumRepository.findByTituloContainingIgnoreCase(query);
    }

    public List<Usuario> searchProfiles(String query) {
        return usuarioRepository.findByNombreContainingIgnoreCase(query);
    }

    public List<Lista> searchPlaylists(String query) {
        return listaRepository.findByTituloContainingIgnoreCase(query);
    }
}
