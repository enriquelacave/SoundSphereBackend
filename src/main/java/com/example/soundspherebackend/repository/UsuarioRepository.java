package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(Login login);
}
