package com.agromercado.agrobackend.notifications.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notificaciones")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioEmail;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private Boolean leida;
}
