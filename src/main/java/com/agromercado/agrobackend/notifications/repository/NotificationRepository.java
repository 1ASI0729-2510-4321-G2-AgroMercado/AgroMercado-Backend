package com.agromercado.agrobackend.notifications.repository;

import com.agromercado.agrobackend.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUsuarioEmail(String usuarioEmail);
    int countByUsuarioEmailAndLeidaFalse(String usuarioEmail);
}
