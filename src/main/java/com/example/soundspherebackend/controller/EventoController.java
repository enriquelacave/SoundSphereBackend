package com.example.soundspherebackend.controller;

import com.example.soundspherebackend.dto.EventoDTO;
import com.example.soundspherebackend.model.Artista;
import com.example.soundspherebackend.model.Evento;
import com.example.soundspherebackend.model.EventoArtista;
import com.example.soundspherebackend.repository.ArtistaRepository;
import com.example.soundspherebackend.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/{idArtista}")
    public ResponseEntity<List<EventoDTO>> getEventsByArtist(@PathVariable Integer idArtista) {
        Optional<Artista> artistaOptional = artistaRepository.findById(idArtista);

        if (artistaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Artista artista = artistaOptional.get();
        Set<EventoArtista> eventoArtistas = artista.getEventoArtistas();
        List<EventoDTO> eventosDTO = eventoArtistas.stream()
                .map(EventoArtista::getEvento)
                .filter(evento -> evento.getFecha().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().isAfter(LocalDate.now())) // Filtrar eventos futuros
                .distinct()
                .sorted((e1, e2) -> e1.getFecha().compareTo(e2.getFecha())) // Ordenar de más próximo a más lejano
                .map(evento -> {
                    EventoDTO eventoDTO = new EventoDTO();
                    eventoDTO.setId(evento.getId());
                    eventoDTO.setTitulo(evento.getTitulo());
                    eventoDTO.setDescripcion(evento.getDescripcion());
                    eventoDTO.setFecha(evento.getFecha());
                    return eventoDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(eventosDTO);
    }

    @GetMapping("/next")
    public ResponseEntity<List<EventoDTO>> getFutureEvents() {
        List<Evento> eventos = eventoRepository.findAllFutureEventsOrderedByDate();
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(evento -> {
                    EventoDTO eventoDTO = new EventoDTO();
                    eventoDTO.setId(evento.getId());
                    eventoDTO.setTitulo(evento.getTitulo());
                    eventoDTO.setDescripcion(evento.getDescripcion());
                    eventoDTO.setFecha(evento.getFecha());
                    return eventoDTO;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(eventosDTO);
    }
}
