package com.agromercado.agrobackend.orders.service;

import com.agromercado.agrobackend.orders.model.Order;
import com.agromercado.agrobackend.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order crearOrden(Order order) {
        order.setFechaOrden(LocalDateTime.now());
        order.setEstado("PENDIENTE");
        return orderRepository.save(order);
    }

    public List<Order> listarOrdenes() {
        return orderRepository.findAll();
    }

    public Optional<Order> obtenerOrdenPorId(Long id) {
        return orderRepository.findById(id);
    }

    public boolean eliminarOrden(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
