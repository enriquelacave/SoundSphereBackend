package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Optional<Follow> findBySeguidorIdAndSeguidoId(Integer seguidorId, Integer seguidoId);
}
