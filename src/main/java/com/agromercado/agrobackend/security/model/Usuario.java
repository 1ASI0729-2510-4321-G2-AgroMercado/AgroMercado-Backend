package com.agromercado.agrobackend.model.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Email(message = "Correo electrónico inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Column(nullable = false, length = 64)
    private String passwordHash;

    @Column(nullable = false)
    private Boolean enabled = true; // ➔ campo añadido con valor por defecto
}
