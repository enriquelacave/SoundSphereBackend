package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.dto.ListaDTO;
import com.example.soundspherebackend.model.*;
import com.example.soundspherebackend.repository.CancionRepository;
import com.example.soundspherebackend.repository.ListaCancionRepository;
import com.example.soundspherebackend.repository.ListaRepository;
import com.example.soundspherebackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ListaCancionRepository listaCancionRepository;

    @Autowired
    private CancionService cancionService;

    public List<ListaDTO> getPlaylistsByUserId(Integer userId) {
        List<Lista> playlists = listaRepository.findByUsuarioId(userId);
        return playlists.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ListaDTO getPlaylistById(Integer listaId) {
        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        return convertToDto(lista);
    }

    private ListaDTO convertToDto(Lista lista) {
        ListaDTO listaDTO = new ListaDTO();
        listaDTO.setId(lista.getId());
        listaDTO.setTitulo(lista.getTitulo());
        listaDTO.setIdUsuario(lista.getUsuario().getId());
        listaDTO.setUrlImagen(lista.getUrlImagen());
        return listaDTO;
    }

    public List<CancionDTO> getSongsByPlaylistId(Integer listaId) {
        List<ListaCancion> listaCanciones = listaCancionRepository.findByListaId(listaId);

        return listaCanciones.stream()
                .map(listaCancion -> convertToDto(listaCancion.getCancion()))
                .collect(Collectors.toList());
    }

    private CancionDTO convertToDto(Cancion cancion) {
        CancionDTO cancionDTO = new CancionDTO();
        cancionDTO.setId(cancion.getId());
        cancionDTO.setTitulo(cancion.getTitulo());
        cancionDTO.setDuracion(cancion.getDuracion());

        // Obtener los artistas usando el servicio de canción
        List<ArtistaDTO> artistas = cancionService.getArtistBySong(cancion.getId());
        cancionDTO.setArtistas(artistas);

        return cancionDTO;
    }
}
