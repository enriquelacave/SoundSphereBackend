package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.ListaDTO;
import com.example.soundspherebackend.model.Lista;
import com.example.soundspherebackend.model.Usuario;
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

    public List<ListaDTO> getPlaylistsByUserId(Integer userId) {
        List<Lista> playlists = listaRepository.findByUsuarioId(userId);
        return playlists.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ListaDTO convertToDto(Lista lista) {
        ListaDTO listaDTO = new ListaDTO();
        listaDTO.setId(lista.getId());
        listaDTO.setTitulo(lista.getTitulo());
        listaDTO.setIdUsuario(lista.getUsuario().getId());
        return listaDTO;
    }
}
