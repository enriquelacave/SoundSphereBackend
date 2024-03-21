package com.example.soundspherebackend.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ComentarioDTO {
    private Integer id;
    private String texto;
    private Date fechaPublicacion;
    private Integer idUsuario; // ID del usuario que hizo el comentario
    private Integer idCancion; // ID de la canción a la que pertenece el comentario
}
