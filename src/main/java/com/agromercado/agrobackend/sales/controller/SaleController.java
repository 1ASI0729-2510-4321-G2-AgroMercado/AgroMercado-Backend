package com.agromercado.agrobackend.sales.controller;

import com.agromercado.agrobackend.sales.model.MensajeVenta;
import com.agromercado.agrobackend.sales.model.Sale;
import com.agromercado.agrobackend.sales.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarVenta(@RequestBody Sale sale) {
        Sale nuevaVenta = saleService.registrarVenta(sale);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeVenta.VENTA_CREADA);
        response.put("venta", nuevaVenta);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarVentas() {
        List<Sale> ventas = saleService.listarVentas();
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeVenta.LISTA_VENTAS);
        response.put("cantidad", ventas.size());
        response.put("ventas", ventas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> obtenerVenta(@PathVariable Long id) {
        return saleService.obtenerVentaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/vendedor/{email}")
    public ResponseEntity<Map<String, Object>> listarVentasPorVendedor(@PathVariable String email) {
        List<Sale> ventas = saleService.listarVentasPorVendedor(email);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeVenta.LISTA_VENTAS);
        response.put("cantidad", ventas.size());
        response.put("ventas", ventas);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarVenta(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        if (saleService.eliminarVenta(id)) {
            response.put("mensaje", MensajeVenta.VENTA_ELIMINADA);
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", MensajeVenta.VENTA_NO_ENCONTRADA);
            return ResponseEntity.status(404).body(response);
        }
    }
}
