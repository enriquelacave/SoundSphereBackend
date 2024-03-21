package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Shares;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharesRepository extends JpaRepository<Shares, Integer> {
}
