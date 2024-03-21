package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
}
