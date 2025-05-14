package com.agromercado.agrobackend.ratings.controller;

import com.agromercado.agrobackend.ratings.model.MensajeRating;
import com.agromercado.agrobackend.ratings.model.Rating;
import com.agromercado.agrobackend.ratings.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarCalificacion(@RequestBody Rating rating) {
        Rating nuevaCalificacion = ratingService.registrarCalificacion(rating);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeRating.CALIFICACION_CREADA);
        response.put("calificacion", nuevaCalificacion);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarCalificaciones() {
        List<Rating> calificaciones = ratingService.listarCalificaciones();
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeRating.LISTA_CALIFICACIONES);
        response.put("cantidad", calificaciones.size());
        response.put("calificaciones", calificaciones);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> obtenerCalificacion(@PathVariable Long id) {
        return ratingService.obtenerCalificacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Map<String, Object>> listarCalificacionesPorProducto(@PathVariable Long productoId) {
        List<Rating> calificaciones = ratingService.listarCalificacionesPorProducto(productoId);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeRating.LISTA_CALIFICACIONES);
        response.put("cantidad", calificaciones.size());
        response.put("calificaciones", calificaciones);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vendedor/{email}")
    public ResponseEntity<Map<String, Object>> listarCalificacionesPorVendedor(@PathVariable String email) {
        List<Rating> calificaciones = ratingService.listarCalificacionesPorVendedor(email);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeRating.LISTA_CALIFICACIONES);
        response.put("cantidad", calificaciones.size());
        response.put("calificaciones", calificaciones);
        return ResponseEntity.ok(response);
    }
}
