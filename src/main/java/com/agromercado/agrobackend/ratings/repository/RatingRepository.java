package com.agromercado.agrobackend.ratings.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.agromercado.agrobackend.ratings.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByProductoId(Long productoId);

    List<Rating> findByVendedorEmail(String vendedorEmail);

    @Query("SELECT AVG(r.puntuacion) FROM Rating r WHERE r.vendedorEmail = :vendedorEmail")
    Double promedioPuntuacionPorVendedor(@Param("vendedorEmail") String vendedorEmail);
}
