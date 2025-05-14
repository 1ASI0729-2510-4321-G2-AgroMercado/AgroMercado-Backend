package com.agromercado.agrobackend.notifications.service;

import com.agromercado.agrobackend.notifications.model.Notification;
import com.agromercado.agrobackend.notifications.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification crearNotificacion(Notification notification) {
        notification.setFechaCreacion(LocalDateTime.now());
        notification.setLeida(false); // Al crear siempre es no leída
        return notificationRepository.save(notification);
    }

    public List<Notification> listarNotificaciones() {
        return notificationRepository.findAll();
    }

    public List<Notification> listarNotificacionesPorUsuario(String email) {
        return notificationRepository.findByUsuarioEmail(email);
    }

    public Optional<Notification> obtenerNotificacionPorId(Long id) {
        return notificationRepository.findById(id);
    }

    public boolean marcarComoLeida(Long id) {
        Optional<Notification> notificacionOpt = notificationRepository.findById(id);
        if (notificacionOpt.isPresent()) {
            Notification notificacion = notificacionOpt.get();
            notificacion.setLeida(true);
            notificationRepository.save(notificacion);
            return true;
        }
        return false;
    }

    public boolean eliminarNotificacion(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
