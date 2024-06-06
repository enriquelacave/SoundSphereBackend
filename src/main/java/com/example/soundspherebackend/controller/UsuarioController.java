package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.AlbumDTO;
import com.example.soundspherebackend.dto.CreateAlbumRequestDTO;
import com.example.soundspherebackend.dto.UsuarioDTO;
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

    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO usuarioDTO) throws ChangeSetPersister.NotFoundException {

        // Convertir UsuarioDTO a Usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setSexo(usuarioDTO.getSexo());

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

        return ResponseEntity.ok(responseDTO);
    }

}
