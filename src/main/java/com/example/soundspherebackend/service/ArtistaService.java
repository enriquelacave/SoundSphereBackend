package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.repository.ArtistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    @Autowired
    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<ArtistaDTO> getAllArtistas() {
        List<Artista> artistas = artistaRepository.findAll();
        return artistas.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ArtistaDTO getArtistaById(Integer id) {
        Optional<Artista> optionalArtista = artistaRepository.findById(id);
        return optionalArtista.map(this::convertToDto).orElse(null);
    }

    public ArtistaDTO createArtista(ArtistaDTO artistaDTO) {
        Artista artista = convertToEntity(artistaDTO);
        Artista savedArtista = artistaRepository.save(artista);
        return convertToDto(savedArtista);
    }

    public ArtistaDTO updateArtista(Integer id, ArtistaDTO artistaDTO) {
        Optional<Artista> optionalArtista = artistaRepository.findById(id);
        if (optionalArtista.isPresent()) {
            Artista existingArtista = optionalArtista.get();
            BeanUtils.copyProperties(artistaDTO, existingArtista, "id");
            Artista updatedArtista = artistaRepository.save(existingArtista);
            return convertToDto(updatedArtista);
        }
        return null;
    }

    public void deleteArtista(Integer id) {
        artistaRepository.deleteById(id);
    }

    private ArtistaDTO convertToDto(Artista artista) {
        ArtistaDTO artistaDTO = new ArtistaDTO();
        BeanUtils.copyProperties(artista, artistaDTO);
        return artistaDTO;
    }

    private Artista convertToEntity(ArtistaDTO artistaDTO) {
        Artista artista = new Artista();
        BeanUtils.copyProperties(artistaDTO, artista);
        return artista;
    }
}
