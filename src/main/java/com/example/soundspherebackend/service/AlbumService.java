package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.dto.CreateAlbumRequestDTO;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.model.AlbumArtista;
import com.example.soundspherebackend.repository.AlbumArtistaRepository;
import com.example.soundspherebackend.repository.AlbumRepository;
import com.example.soundspherebackend.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private AlbumArtistaRepository albumArtistaRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AlbumDTO getAlbumById(Integer albumId) {
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        if (optionalAlbum.isPresent()) {
            return convertToDTO(optionalAlbum.get());
        }
        return null;
    }


    public AlbumDTO createAlbum(AlbumDTO albumDTO, List<Integer> idArtistas) {
        // Convertir el DTO del álbum a una entidad de álbum
        Album album = convertToEntity(albumDTO);

        // Guardar el álbum en la base de datos
        album = albumRepository.save(album);

// Crear registros en la tabla AlbumArtista para asociar artistas al álbum
        if (idArtistas != null && !idArtistas.isEmpty()) {
            album.setArtistas(new HashSet<>());
            for (Integer idArtista : idArtistas) {
                Artista artista = artistaRepository.findById(idArtista)
                        .orElseThrow(() -> new RuntimeException("Artista no encontrado con ID: " + idArtista));

                // Crear una nueva instancia de AlbumArtista y asignar el álbum y el artista
                AlbumArtista albumArtista = new AlbumArtista();
                albumArtista.setAlbum(album);
                albumArtista.setArtista(artista);

                // Agregar el AlbumArtista a la lista de AlbumArtistas del álbum
                albumArtistaRepository.save(albumArtista);
            }
        }

        return convertToDTO(album);
    }


    public AlbumDTO updateAlbum(Integer idAlbum, AlbumDTO albumDTO) {
        Optional<Album> optionalAlbum = albumRepository.findById(idAlbum);
        if (optionalAlbum.isPresent()) {
            Album album = optionalAlbum.get();

            album.setTitulo(albumDTO.getTitulo());
            album.setUrlImagen(albumDTO.getUrlImagen());
            album.setFechaPublicacion(albumDTO.getFechaPublicacion());

            album = albumRepository.save(album);

            return convertToDTO(album);
        }
        return null;
    }

    public void deleteAlbum(Integer idAlbum) {
        albumRepository.deleteById(idAlbum);
    }

    private Album convertToEntity(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setId(albumDTO.getId());
        album.setTitulo(albumDTO.getTitulo());
        album.setUrlImagen(albumDTO.getUrlImagen());
        album.setFechaPublicacion(albumDTO.getFechaPublicacion());
        return album;
    }

    private AlbumDTO convertToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setTitulo(album.getTitulo());
        albumDTO.setUrlImagen(album.getUrlImagen());
        albumDTO.setFechaPublicacion(album.getFechaPublicacion());
        return albumDTO;
    }

    public List<AlbumDTO> getLast20Albums() {
        List<Object[]> results = albumRepository.findLast20Albums();
        return results.stream()
                .map(this::mapToAlbumDTO)
                .collect(Collectors.toList());
    }

    private AlbumDTO mapToAlbumDTO(Object[] row) {
        AlbumDTO album = new AlbumDTO();
        album.setId((Integer) row[0]);
        album.setTitulo((String) row[1]);
        album.setUrlImagen((String) row[2]);
        album.setFechaPublicacion(((java.sql.Date) row[3]).toLocalDate());

        ArtistaDTO artista = new ArtistaDTO();
        artista.setId((Integer) row[4]);
        artista.setNombre((String) row[5]);

        album.setArtistas(Collections.singletonList(artista));
        return album;
    }
}
