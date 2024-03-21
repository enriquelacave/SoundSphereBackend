package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.model.Cancion;
import com.example.soundspherebackend.repository.AlbumRepository;
import com.example.soundspherebackend.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public List<Cancion> listarAlbumes(){
        return cancionRepository.findAll();
    }

    /**
     * Crear un álbum
     *
     *
     */
    public String crearCancion(CancionDTO cancionDTO){
        Cancion nuevaCancion = new Cancion();
        nuevaCancion.setTitulo(cancionDTO.getTitulo());
        nuevaCancion.setDuracion(cancionDTO.getDuracion());
        nuevaCancion.setUrl(cancionDTO.getUrl());

        Album album = albumRepository.findById(cancionDTO.getIdAlbum()).orElse(null);

        if (album == null){
            return "No se ha creado la canción, no se ha encontrado el album";
        }else{
            nuevaCancion.setAlbum(album);
            cancionRepository.save(nuevaCancion);
            return "Se ha creado el registro correctamente";
        }
    }
}
