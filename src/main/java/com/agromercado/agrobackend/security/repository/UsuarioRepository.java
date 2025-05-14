package com.agromercado.agrobackend.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agromercado.agrobackend.model.security.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
