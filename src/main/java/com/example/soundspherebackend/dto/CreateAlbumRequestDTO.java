package com.example.soundspherebackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateAlbumRequestDTO {
    private AlbumDTO albumDTO;
    private List<Integer> idArtistas;
}
