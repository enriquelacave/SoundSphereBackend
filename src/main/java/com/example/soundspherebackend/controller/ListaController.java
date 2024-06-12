package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.dto.ListaCancionDTO;
import com.example.soundspherebackend.dto.ListaDTO;
import com.example.soundspherebackend.model.*;
import com.example.soundspherebackend.repository.CancionRepository;
import com.example.soundspherebackend.repository.ListaCancionRepository;
import com.example.soundspherebackend.repository.ListaRepository;
import com.example.soundspherebackend.repository.UsuarioRepository;
import com.example.soundspherebackend.service.ListaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{listaId}")
    public ResponseEntity<ListaDTO> getPlaylist(@PathVariable Integer listaId) {
        ListaDTO lista = listaService.getPlaylistById(listaId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/songs/{listaId}")
    public ResponseEntity<List<CancionDTO>> getSongsByPlaylist(@PathVariable Integer listaId) {
        List<CancionDTO> canciones = listaService.getSongsByPlaylistId(listaId);
        return ResponseEntity.ok(canciones);
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
    public ResponseEntity<?> addSongToPlaylist(@RequestBody ListaCancionDTO listaCancionDTO) throws ChangeSetPersister.NotFoundException {

        // Verificar si la canción ya está en la playlist
        if (listaRepository.existsByListaIdAndCancionId(listaCancionDTO.getIdLista(), listaCancionDTO.getIdCancion())) {
            return ResponseEntity.badRequest().body("La canción ya está en la playlist");
        }

        ListaCancion listaCancion = new ListaCancion();

        // Obtener la playlist asociada
        Lista lista = listaRepository.findById(listaCancionDTO.getIdLista())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        listaCancion.setLista(lista);

        // Obtener la cancion asociada
        Cancion cancion = cancionRepository.findById(listaCancionDTO.getIdCancion())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        listaCancion.setCancion(cancion);

        // Guardar el registro en la base de datos
        ListaCancion listaCancionNueva = listaCancionRepository.save(listaCancion);

        // Convertir de nuevo a DTO para la respuesta
        ListaCancionDTO responseDTO = new ListaCancionDTO();
        responseDTO.setId(listaCancionNueva.getId());
        responseDTO.setIdLista(listaCancionNueva.getLista().getId());
        responseDTO.setIdCancion(listaCancion.getCancion().getId());

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/edit/{listaId}")
    public ResponseEntity<ListaDTO> editPlaylist(@PathVariable Integer listaId, @RequestBody ListaDTO listaDTO, @RequestHeader("userId") Integer userId) {
        Lista lista = listaRepository.findById(listaId).orElse(null);
        if (lista != null && lista.getUsuario().getId().equals(userId)) {
            lista.setTitulo(listaDTO.getTitulo());
            lista.setUrlImagen(listaDTO.getUrlImagen());
            listaRepository.save(lista);

            // Crear una instancia de ListaDTO con los datos actualizados de la lista
            ListaDTO updatedListaDTO = new ListaDTO();
            updatedListaDTO.setId(lista.getId());
            updatedListaDTO.setTitulo(lista.getTitulo());
            updatedListaDTO.setUrlImagen(lista.getUrlImagen());
            updatedListaDTO.setIdUsuario(lista.getUsuario().getId());

            return ResponseEntity.ok(updatedListaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @DeleteMapping("/delete/{listaId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer listaId, @RequestHeader("userId") Integer userId) {
        Lista lista = listaRepository.findById(listaId).orElse(null);
        if (lista != null && lista.getUsuario().getId().equals(userId)) {
            // Eliminar todos los registros en ListaCancion asociados con la lista
            listaCancionRepository.deleteByListaId(listaId);
            // Eliminar la lista
            listaRepository.deleteById(listaId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove-song/{listaId}/{cancionId}")
    public ResponseEntity<String> removeSongFromPlaylist(@PathVariable Integer listaId, @PathVariable Integer cancionId) {
        try {
            listaCancionRepository.deleteByListaIdAndCancionId(listaId, cancionId);
            return ResponseEntity.ok("Canción eliminada de la lista correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la canción de la lista.");
        }
    }
}
