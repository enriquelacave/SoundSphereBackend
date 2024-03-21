package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
}
