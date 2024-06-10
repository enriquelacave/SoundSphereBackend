package com.example.soundspherebackend.service;

import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.model.Cancion;
import com.example.soundspherebackend.model.Usuario;
import com.example.soundspherebackend.repository.AlbumRepository;
import com.example.soundspherebackend.repository.ArtistaRepository;
import com.example.soundspherebackend.repository.CancionRepository;
import com.example.soundspherebackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final CancionRepository cancionRepository;
    private final ArtistaRepository artistaRepository;
    private final AlbumRepository albumRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public SearchService(CancionRepository cancionRepository, ArtistaRepository artistaRepository,
                         AlbumRepository albumRepository, UsuarioRepository usuarioRepository) {
        this.cancionRepository = cancionRepository;
        this.artistaRepository = artistaRepository;
        this.albumRepository = albumRepository;
        this.usuarioRepository = usuarioRepository;
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
}
