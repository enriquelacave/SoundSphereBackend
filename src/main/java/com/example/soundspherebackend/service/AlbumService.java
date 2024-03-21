package com.example.soundspherebackend.service;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    /**
     * Lista todos los álbumes
     *
     * @return
     */
    public List<Album> listarAlbumes(){
        return albumRepository.findAll();
    }

    /**
     * Crear un álbum
     *
     *
     */
    public String crearAlbum(AlbumDTO albumDTO){
        Album nuevoAlbum = new Album();
            nuevoAlbum.setTitulo(albumDTO.getTitulo());
            nuevoAlbum.setUrlImagen(albumDTO.getUrl_imagen());
            nuevoAlbum.setFechaPublicacion(albumDTO.getFecha_publicacion());
            albumRepository.save(nuevoAlbum);
            return "Producto creado correctamente";
    }

}
