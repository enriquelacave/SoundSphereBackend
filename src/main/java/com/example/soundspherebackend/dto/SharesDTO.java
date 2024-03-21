package com.example.soundspherebackend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SharesDTO {
    private Integer id;
    private Integer emisorId;
    private Integer receptorId;
    private Integer tipo;
    private Long idElemento;
    private Date fecha;
}
