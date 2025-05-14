package com.agromercado.agrobackend.notifications.controller;

import com.agromercado.agrobackend.notifications.model.MensajeNotificacion;
import com.agromercado.agrobackend.notifications.model.Notification;
import com.agromercado.agrobackend.notifications.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearNotificacion(@RequestBody Notification notification) {
        Notification nueva = notificationService.crearNotificacion(notification);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeNotificacion.NOTIFICACION_CREADA);
        response.put("notificacion", nueva);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarNotificaciones() {
        List<Notification> notificaciones = notificationService.listarNotificaciones();
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeNotificacion.LISTA_NOTIFICACIONES);
        response.put("cantidad", notificaciones.size());
        response.put("notificaciones", notificaciones);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario/{email}")
    public ResponseEntity<Map<String, Object>> listarNotificacionesPorUsuario(@PathVariable String email) {
        List<Notification> notificaciones = notificationService.listarNotificacionesPorUsuario(email);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeNotificacion.LISTA_NOTIFICACIONES);
        response.put("cantidad", notificaciones.size());
        response.put("notificaciones", notificaciones);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/leer")
    public ResponseEntity<Map<String, String>> marcarComoLeida(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        if (notificationService.marcarComoLeida(id)) {
            response.put("mensaje", MensajeNotificacion.NOTIFICACION_MARCADA);
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", MensajeNotificacion.NOTIFICACION_NO_ENCONTRADA);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarNotificacion(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        if (notificationService.eliminarNotificacion(id)) {
            response.put("mensaje", MensajeNotificacion.NOTIFICACION_ELIMINADA);
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", MensajeNotificacion.NOTIFICACION_NO_ENCONTRADA);
            return ResponseEntity.status(404).body(response);
        }
    }
}
