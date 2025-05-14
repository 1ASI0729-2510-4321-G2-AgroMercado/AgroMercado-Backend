package com.agromercado.agrobackend.ratings.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "calificaciones")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    private String vendedorEmail;

    private String compradorEmail;

    private Integer puntuacion;

    private String comentario;

    private LocalDateTime fechaCalificacion;
}
