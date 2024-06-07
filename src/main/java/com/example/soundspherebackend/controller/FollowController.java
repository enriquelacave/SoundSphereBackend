package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.FollowDTO;
import com.example.soundspherebackend.model.Follow;
import com.example.soundspherebackend.model.Usuario;
import com.example.soundspherebackend.repository.FollowRepository;
import com.example.soundspherebackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/toggle")
    public ResponseEntity<?> toggleFollow(@RequestBody FollowDTO followDTO) {
        Optional<Follow> existingFollow = followRepository.findBySeguidorIdAndSeguidoId(followDTO.getSeguidorId(), followDTO.getSeguidoId());
        if (existingFollow.isPresent()) {
            followRepository.delete(existingFollow.get());
            return ResponseEntity.ok().body("{\"message\": \"Unfollowed successfully\"}");
        } else {
            Follow follow = new Follow();
            Usuario seguidor = usuarioRepository.findById(followDTO.getSeguidorId())
                    .orElseThrow(() -> new RuntimeException("Seguidor not found"));
            Usuario seguido = usuarioRepository.findById(followDTO.getSeguidoId())
                    .orElseThrow(() -> new RuntimeException("Seguido not found"));

            follow.setSeguidor(seguidor);
            follow.setSeguido(seguido);
            followRepository.save(follow);
            return ResponseEntity.ok().body("{\"message\": \"Followed successfully\"}");
        }
    }

    @GetMapping("/check/{seguidorId}/{seguidoId}")
    public ResponseEntity<Boolean> checkIfFollowing(@PathVariable Integer seguidorId, @PathVariable Integer seguidoId) {
        Optional<Follow> existingFollow = followRepository.findBySeguidorIdAndSeguidoId(seguidorId, seguidoId);
        return ResponseEntity.ok(existingFollow.isPresent());
    }
}
