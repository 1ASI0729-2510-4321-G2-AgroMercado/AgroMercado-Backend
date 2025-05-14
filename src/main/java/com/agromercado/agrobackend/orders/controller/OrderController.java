package com.agromercado.agrobackend.orders.controller;

import com.agromercado.agrobackend.orders.model.MensajeOrden;
import com.agromercado.agrobackend.orders.model.Order;
import com.agromercado.agrobackend.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearOrden(@RequestBody Order order) {
        Order nuevaOrden = orderService.crearOrden(order);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeOrden.ORDEN_CREADA);
        response.put("orden", nuevaOrden);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarOrdenes() {
        List<Order> ordenes = orderService.listarOrdenes();
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeOrden.LISTA_ORDENES);
        response.put("cantidad", ordenes.size());
        response.put("ordenes", ordenes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> obtenerOrden(@PathVariable Long id) {
        return orderService.obtenerOrdenPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarOrden(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        if (orderService.eliminarOrden(id)) {
            response.put("mensaje", MensajeOrden.ORDEN_ELIMINADA);
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", MensajeOrden.ORDEN_NO_ENCONTRADA);
            return ResponseEntity.status(404).body(response);
        }
    }
}
