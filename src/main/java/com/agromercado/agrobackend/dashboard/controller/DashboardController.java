package com.agromercado.agrobackend.dashboard.controller;

import com.agromercado.agrobackend.dashboard.model.DashboardResumen;
import com.agromercado.agrobackend.dashboard.model.MensajeDashboard;
import com.agromercado.agrobackend.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Map<String, Object>> obtenerResumen(@PathVariable String email) {
        DashboardResumen resumen = dashboardService.obtenerResumenPorUsuario(email);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", MensajeDashboard.RESUMEN_OBTENIDO);
        response.put("resumen", resumen);

        return ResponseEntity.ok(response);
    }
}
