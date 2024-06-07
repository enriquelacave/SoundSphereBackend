package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.ListaCancionDTO;
import com.example.soundspherebackend.dto.ListaDTO;
import com.example.soundspherebackend.model.*;
import com.example.soundspherebackend.repository.CancionRepository;
import com.example.soundspherebackend.repository.ListaCancionRepository;
import com.example.soundspherebackend.repository.ListaRepository;
import com.example.soundspherebackend.repository.UsuarioRepository;
import com.example.soundspherebackend.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@CrossOrigin(origins = "http://localhost:4200")
public class ListaController {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ListaCancionRepository listaCancionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private ListaService listaService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ListaDTO>> getPlaylistsByUserId(@PathVariable Integer userId) {
        List<ListaDTO> listas = listaService.getPlaylistsByUserId(userId);
        return ResponseEntity.ok(listas);
    }

    @PostMapping("/create")
    public ResponseEntity<ListaDTO> createPlaylist(@RequestBody ListaDTO listaDTO) throws ChangeSetPersister.NotFoundException {

        Lista lista = new Lista();
        lista.setTitulo(listaDTO.getTitulo());

        // Obtener el login asociado
        Usuario usuario = usuarioRepository.findById(listaDTO.getIdUsuario())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        lista.setUsuario(usuario);

        // Guardar el usuario en la base de datos
        Lista listaNueva = listaRepository.save(lista);

        // Convertir de nuevo a DTO para la respuesta
        ListaDTO responseDTO = new ListaDTO();
        responseDTO.setId(listaNueva.getId());
        responseDTO.setTitulo(listaNueva.getTitulo());
        responseDTO.setIdUsuario(listaNueva.getUsuario().getId());

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<ListaCancionDTO> addSongToPlaylist(@RequestBody ListaCancionDTO listaCancionDTO) throws ChangeSetPersister.NotFoundException {

        ListaCancion listaCancion = new ListaCancion();

        // Obtener la cancion asociada
        Lista lista = listaRepository.findById(listaCancionDTO.getIdLista())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        listaCancion.setLista(lista);

        // Obtener la cancion asociada
        Cancion cancion = cancionRepository.findById(listaCancionDTO.getIdCancion())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        listaCancion.setCancion(cancion);

        // Guardar el usuario en la base de datos
        ListaCancion listaCancionNueva = listaCancionRepository.save(listaCancion);

        // Convertir de nuevo a DTO para la respuesta
        ListaCancionDTO responseDTO = new ListaCancionDTO();
        responseDTO.setId(listaCancionNueva.getId());
        responseDTO.setIdLista(listaCancionNueva.getLista().getId());
        responseDTO.setIdCancion(listaCancion.getCancion().getId());

        return ResponseEntity.ok(responseDTO);
    }
}
