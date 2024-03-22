package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.AlbumArtistaDTO;
import com.example.soundspherebackend.model.AlbumArtista;
import com.example.soundspherebackend.repository.AlbumArtistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumArtistaService {

    private final AlbumArtistaRepository albumArtistaRepository;

    @Autowired
    public AlbumArtistaService(AlbumArtistaRepository albumArtistaRepository) {
        this.albumArtistaRepository = albumArtistaRepository;
    }

    public List<AlbumArtistaDTO> getAllAlbumArtistas() {
        List<AlbumArtista> albumArtistas = albumArtistaRepository.findAll();
        return albumArtistas.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AlbumArtistaDTO getAlbumArtistaById(Integer id) {
        Optional<AlbumArtista> optionalAlbumArtista = albumArtistaRepository.findById(id);
        return optionalAlbumArtista.map(this::convertToDto).orElse(null);
    }

    public AlbumArtistaDTO createAlbumArtista(AlbumArtistaDTO albumArtistaDTO) {
        AlbumArtista albumArtista = convertToEntity(albumArtistaDTO);
        AlbumArtista savedAlbumArtista = albumArtistaRepository.save(albumArtista);
        return convertToDto(savedAlbumArtista);
    }

    public AlbumArtistaDTO updateAlbumArtista(Integer id, AlbumArtistaDTO albumArtistaDTO) {
        Optional<AlbumArtista> optionalAlbumArtista = albumArtistaRepository.findById(id);
        if (optionalAlbumArtista.isPresent()) {
            AlbumArtista existingAlbumArtista = optionalAlbumArtista.get();
            BeanUtils.copyProperties(albumArtistaDTO, existingAlbumArtista, "id");
            AlbumArtista updatedAlbumArtista = albumArtistaRepository.save(existingAlbumArtista);
            return convertToDto(updatedAlbumArtista);
        }
        return null;
    }

    public void deleteAlbumArtista(Integer id) {
        albumArtistaRepository.deleteById(id);
    }

    private AlbumArtistaDTO convertToDto(AlbumArtista albumArtista) {
        AlbumArtistaDTO albumArtistaDTO = new AlbumArtistaDTO();
        BeanUtils.copyProperties(albumArtista, albumArtistaDTO);
        return albumArtistaDTO;
    }

    private AlbumArtista convertToEntity(AlbumArtistaDTO albumArtistaDTO) {
        AlbumArtista albumArtista = new AlbumArtista();
        BeanUtils.copyProperties(albumArtistaDTO, albumArtista);
        return albumArtista;
    }
}
