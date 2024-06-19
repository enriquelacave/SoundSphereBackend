package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.model.Reproduccion;
import com.example.soundspherebackend.repository.ReproduccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReproduccionService {

    @Autowired
    private ReproduccionRepository reproduccionRepository;

    @Autowired
    private CancionService cancionService;

    public List<CancionDTO> getLast10UniqueSongsByUser(Integer idUsuario) {
        List<Reproduccion> reproducciones = reproduccionRepository.findTop10DistinctByUsuarioIdOrderByFechaPublicacionDesc(idUsuario);

        return reproducciones.stream()
                .map(reproduccion -> {
                    CancionDTO dto = new CancionDTO();
                    dto.setId(reproduccion.getCancion().getId());
                    dto.setTitulo(reproduccion.getCancion().getTitulo());
                    dto.setUrlImagenAlbum(reproduccion.getCancion().getAlbum().getUrlImagen());
                    dto.setDuracion(reproduccion.getCancion().getDuracion());
                    dto.setArtistas(cancionService.getArtistBySong(reproduccion.getCancion().getId()));
                    dto.setIdAlbum(reproduccion.getCancion().getAlbum().getId());
                    return dto;
                })
                .distinct()
                .limit(10)
                .collect(Collectors.toList());
    }
}
