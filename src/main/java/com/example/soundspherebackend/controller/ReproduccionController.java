package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.CancionDTO;
import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.model.Reproduccion;
import com.example.soundspherebackend.model.Usuario;
import com.example.soundspherebackend.model.Cancion;
import com.example.soundspherebackend.repository.LoginRepository;
import com.example.soundspherebackend.repository.ReproduccionRepository;
import com.example.soundspherebackend.repository.UsuarioRepository;
import com.example.soundspherebackend.repository.CancionRepository;
import com.example.soundspherebackend.service.ReproduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reproduction")
public class ReproduccionController {

    private final ReproduccionRepository reproduccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final CancionRepository cancionRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public ReproduccionController(ReproduccionRepository reproduccionRepository,
                                  UsuarioRepository usuarioRepository,
                                  CancionRepository cancionRepository, LoginRepository loginRepository) {
        this.reproduccionRepository = reproduccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.cancionRepository = cancionRepository;
        this.loginRepository = loginRepository;
    }

    @Autowired
    private ReproduccionService reproduccionService;

    @GetMapping("/last/{idUsuario}")
    public List<CancionDTO> getLast10UniqueSongsByUser(@PathVariable Integer idUsuario) {
        return reproduccionService.getLast10UniqueSongsByUser(idUsuario);
    }

    @PostMapping("/{songId}/{idLogin}")
    public void createReproduccion(@PathVariable Integer songId,
                                   @PathVariable Integer idLogin) {
        // Buscar el login asociado al idLogin
        Optional<Login> loginOptional = loginRepository.findById(idLogin);
        if (!loginOptional.isPresent()) {
            return;
        }
        Login login = loginOptional.get();

        // Buscar al usuario por el login
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if (!usuarioOptional.isPresent()) {
            return;
        }
        Usuario usuario = usuarioOptional.get();

        // Buscar la canción por su ID
        Optional<Cancion> cancionOptional = cancionRepository.findById(songId);
        if (!cancionOptional.isPresent()) {
            // Manejar el caso cuando no se encuentra la canción para el id proporcionado
            // Aquí podrías lanzar una excepción, retornar un mensaje de error, etc.
            return;
        }
        Cancion cancion = cancionOptional.get();

        // Crear una nueva instancia de Reproduccion
        Reproduccion reproduccion = new Reproduccion();
        reproduccion.setFechaPublicacion(new Date());
        reproduccion.setUsuario(usuario);
        reproduccion.setCancion(cancion);

        // Guardar la reproducción en la base de datos
        reproduccionRepository.save(reproduccion);
    }
}
