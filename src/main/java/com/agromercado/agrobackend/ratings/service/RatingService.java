package com.agromercado.agrobackend.ratings.service;

import com.agromercado.agrobackend.ratings.model.Rating;
import com.agromercado.agrobackend.ratings.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating registrarCalificacion(Rating rating) {
        rating.setFechaCalificacion(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    public List<Rating> listarCalificaciones() {
        return ratingRepository.findAll();
    }

    public Optional<Rating> obtenerCalificacionPorId(Long id) {
        return ratingRepository.findById(id);
    }

    public List<Rating> listarCalificacionesPorProducto(Long productoId) {
        return ratingRepository.findByProductoId(productoId);
    }

    public List<Rating> listarCalificacionesPorVendedor(String vendedorEmail) {
        return ratingRepository.findByVendedorEmail(vendedorEmail);
    }
}
