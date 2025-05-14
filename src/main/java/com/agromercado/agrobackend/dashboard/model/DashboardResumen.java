package com.agromercado.agrobackend.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResumen {

    private int cantidadCompras;
    private int cantidadVentas;
    private int cantidadProductosPublicados;
    private double promedioCalificaciones;
    private int cantidadNotificacionesNoLeidas;
}
