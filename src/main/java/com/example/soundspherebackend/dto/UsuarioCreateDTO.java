package com.example.soundspherebackend.dto;

import com.example.soundspherebackend.model.Enum.Sexo;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UsuarioCreateDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidos;

    @NotNull
    private Date fechaNacimiento;

    @NotNull
    private Sexo sexo;

    private Integer idLogin;}
