package com.example.soundspherebackend.dto;

import com.example.soundspherebackend.model.Enum.Sexo;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private Sexo sexo;
    private Integer loginId;
    private String urlImagen;
    private Set<Integer> seguidosIds;
    private Set<Integer> seguidoresIds;
    private Set<Integer> listasSeguidasIds;
    private List<Integer> emitidosIds;
    private List<Integer> recibidosIds;
    private List<Integer> likesIds;
    private List<Integer> comentariosIds;
    private List<Integer> reproduccionesIds;
    private Set<Integer> listasCreadasIds;
}
