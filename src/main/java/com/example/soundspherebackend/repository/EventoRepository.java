package com.example.soundspherebackend.repository;

import com.example.soundspherebackend.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("SELECT e FROM Evento e WHERE e.fecha > CURRENT_TIMESTAMP ORDER BY e.fecha ASC")
    List<Evento> findAllFutureEventsOrderedByDate();
}
