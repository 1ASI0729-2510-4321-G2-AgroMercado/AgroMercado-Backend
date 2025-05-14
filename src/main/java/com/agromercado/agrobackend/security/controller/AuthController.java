package com.agromercado.agrobackend.controller.security;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.agromercado.agrobackend.service.security.AuthService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/security/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @Data
    static class RegisterDto {
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        @Email(message = "Email inválido")
        @NotBlank(message = "El email es obligatorio")
        private String email;

        @Size(min = 7, message = "La contraseña debe tener al menos 7 caracteres")
        private String password;
    }

    @Data
    static class LoginDto {
        @Email(message = "Email inválido")
        @NotBlank(message = "El email es obligatorio")
        private String email;

        @Size(min = 7, message = "La contraseña debe tener al menos 7 caracteres")
        private String password;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {
        String result = authService.register(dto.getNombre(), dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto) {
        String result = authService.login(dto.getEmail(), dto.getPassword());
        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
