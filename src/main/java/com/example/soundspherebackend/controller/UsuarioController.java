package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.CreateAlbumRequestDTO;
import com.example.soundspherebackend.dto.ListaDTO;
import com.example.soundspherebackend.dto.UsuarioDTO;
import com.example.soundspherebackend.model.Lista;
import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.model.Usuario;
import com.example.soundspherebackend.repository.LoginRepository;
import com.example.soundspherebackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<UsuarioDTO> getUserData(@PathVariable Integer userId) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(userId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            // Asumiendo que UsuarioDTO tiene los mismos campos que Usuario
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setApellidos(usuario.getApellidos());
            usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioDTO.setSexo(usuario.getSexo());
            usuarioDTO.setLoginId(usuario.getLogin().getId());
            usuarioDTO.setUrlImagen(usuario.getUrlImagen());


            return ResponseEntity.ok(usuarioDTO);
        } else {
            // Manejo del caso en el que el usuario no sea encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO usuarioDTO) throws ChangeSetPersister.NotFoundException {

        // Convertir UsuarioDTO a Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setSexo(usuarioDTO.getSexo());
        usuario.setUrlImagen(usuario.getUrlImagen());

        // Obtener el login asociado
        Login login = loginRepository.findById(usuarioDTO.getLoginId())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        usuario.setLogin(login);

        // Guardar el usuario en la base de datos
        Usuario usuarioNuevo = usuarioRepository.save(usuario);

        // Convertir de nuevo a DTO para la respuesta
        UsuarioDTO responseDTO = new UsuarioDTO();
        responseDTO.setId(usuarioNuevo.getId());
        responseDTO.setLoginId(usuarioNuevo.getLogin().getId());
        responseDTO.setNombre(usuarioNuevo.getNombre());
        responseDTO.setApellidos(usuarioNuevo.getApellidos());
        responseDTO.setFechaNacimiento(usuarioNuevo.getFechaNacimiento());
        responseDTO.setSexo(usuarioNuevo.getSexo());
        responseDTO.setUrlImagen(usuarioNuevo.getUrlImagen());

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<UsuarioDTO> editUser(@PathVariable Integer userId, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);

        assert usuario != null;
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellidos(usuarioDTO.getApellidos());
            usuario.setUrlImagen(usuarioDTO.getUrlImagen());
            usuarioRepository.save(usuario);

            // Crear una instancia de ListaDTO con los datos actualizados de la lista
            UsuarioDTO updatedUsuarioDTO = new UsuarioDTO();
            updatedUsuarioDTO.setId(usuario.getId());
            updatedUsuarioDTO.setNombre(usuario.getNombre());
            updatedUsuarioDTO.setApellidos(usuario.getApellidos());
            updatedUsuarioDTO.setUrlImagen(usuario.getUrlImagen());

            return ResponseEntity.ok(updatedUsuarioDTO);
    }

}
