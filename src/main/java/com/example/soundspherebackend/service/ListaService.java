package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.ArtistaDTO;
import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.dto.ListaDTO;
import com.example.soundspherebackend.dto.UsuarioDTO;
import com.example.soundspherebackend.model.*;
import com.example.soundspherebackend.repository.*;
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

    @Autowired
    private FollowRepository followRepository;

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
        cancionDTO.setUrlImagenAlbum(cancion.getAlbum().getUrlImagen());
        cancionDTO.setIdAlbum(cancion.getAlbum().getId());

        // Obtener los artistas usando el servicio de canción
        List<ArtistaDTO> artistas = cancionService.getArtistBySong(cancion.getId());
        cancionDTO.setArtistas(artistas);

        return cancionDTO;
    }

    public List<ListaDTO> getPlaylistsOfFollowedUsers(Integer userId) {
        // Obtener los IDs de los usuarios seguidos por el usuario dado
        List<Integer> followedUserIds = followRepository.findFollowedUserIdsBySeguidorId(userId);

        // Obtener las playlists de los usuarios seguidos
        List<Lista> playlists = listaRepository.findByUsuarioIdIn(followedUserIds);

        // Convertir las playlists a DTOs
        List<ListaDTO> playlistDTOs = playlists.stream().map(playlist -> {
            ListaDTO dto = new ListaDTO();
            dto.setId(playlist.getId());
            dto.setTitulo(playlist.getTitulo());
            dto.setIdUsuario(playlist.getUsuario().getId());
            dto.setUrlImagen(playlist.getUrlImagen());

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(playlist.getUsuario().getId());
            usuarioDTO.setNombre(playlist.getUsuario().getNombre());
            usuarioDTO.setApellidos(playlist.getUsuario().getApellidos());
            usuarioDTO.setUrlImagen(playlist.getUsuario().getUrlImagen());
            dto.setUsuario(usuarioDTO);

            return dto;
        }).collect(Collectors.toList());

        return playlistDTOs;
    }
}
