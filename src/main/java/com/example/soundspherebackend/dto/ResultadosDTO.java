package com.example.soundspherebackend.dto;

import com.example.soundspherebackend.model.Album;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.model.Cancion;
import com.example.soundspherebackend.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadosDTO {
    private List<CancionDTO> canciones;
    private List<ArtistaDTO> artistas;
    private List<AlbumDTO> albums;
    private List<UsuarioDTO> usuarios;
}
