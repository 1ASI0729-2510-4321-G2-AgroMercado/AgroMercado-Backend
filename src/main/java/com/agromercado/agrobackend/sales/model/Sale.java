package com.agromercado.agrobackend.sales.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ventas")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    private String compradorEmail;

    private String vendedorEmail;

    private Integer cantidadVendida;

    private Double precioTotal;

    private LocalDateTime fechaVenta;
}
