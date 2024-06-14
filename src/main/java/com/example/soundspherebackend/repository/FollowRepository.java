package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Optional<Follow> findBySeguidorIdAndSeguidoId(Integer seguidorId, Integer seguidoId);

    @Query("SELECT f.seguido.id FROM Follow f WHERE f.seguidor.id = :userId")
    List<Integer> findFollowedUserIdsBySeguidorId(@Param("userId") Integer userId);
}
