package com.agromercado.agrobackend.service.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agromercado.agrobackend.model.security.Usuario;
import com.agromercado.agrobackend.repository.security.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public String register(String nombre, String email, String password) {
        log.info("Iniciando registro para: {}", email);
        validarCampos(nombre, email, password);

        if (usuarioRepository.existsByEmail(email)) {
            log.warn("El email {} ya está registrado", email);
            throw new IllegalArgumentException("Email ya registrado");
        }

        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);
        u.setPasswordHash(hash(password));
        u.setEnabled(true); // Se asegura que el nuevo usuario esté habilitado

        log.info("Guardando usuario en base de datos...");
        usuarioRepository.save(u);

        log.info("Usuario {} registrado exitosamente", email);
        return "Registro exitoso";
    }

    public String login(String email, String password) {
        validarCampos("tmp", email, password); // Nombre no es necesario para login

        Usuario u = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (!u.getPasswordHash().equals(hash(password))) {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        return "Login correcto";
    }

    private void validarCampos(String nombre, String email, String password) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email obligatorio");
        }
        if (password == null || password.length() < 7) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 7 caracteres");
        }
    }

    private String hash(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plain.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("Error al crear hash de contraseña", e);
            throw new RuntimeException("SHA-256 no disponible", e);
        }
    }
}
