package com.agromercado.agrobackend.orders.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ordenes")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    private String compradorEmail;

    private Integer cantidad;

    private LocalDateTime fechaOrden;

    private String estado; // PENDIENTE, CONFIRMADO
}
