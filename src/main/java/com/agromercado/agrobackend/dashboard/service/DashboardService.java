package com.agromercado.agrobackend.dashboard.service;

import com.agromercado.agrobackend.catalog.repository.ProductoRepository;
import com.agromercado.agrobackend.notifications.repository.NotificationRepository;
import com.agromercado.agrobackend.orders.repository.OrderRepository;
import com.agromercado.agrobackend.ratings.repository.RatingRepository;
import com.agromercado.agrobackend.sales.repository.SaleRepository;
import com.agromercado.agrobackend.dashboard.model.DashboardResumen;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ProductoRepository productoRepository;
    private final OrderRepository orderRepository;
    private final SaleRepository saleRepository;
    private final RatingRepository ratingRepository;
    private final NotificationRepository notificationRepository;

    public DashboardService(ProductoRepository productoRepository,
                             OrderRepository orderRepository,
                             SaleRepository saleRepository,
                             RatingRepository ratingRepository,
                             NotificationRepository notificationRepository) {
        this.productoRepository = productoRepository;
        this.orderRepository = orderRepository;
        this.saleRepository = saleRepository;
        this.ratingRepository = ratingRepository;
        this.notificationRepository = notificationRepository;
    }

    public DashboardResumen obtenerResumenPorUsuario(String email) {
        int compras = orderRepository.countByCompradorEmail(email);
        int productos = productoRepository.countByVendedorEmail(email);
        int ventas = saleRepository.countByVendedorEmail(email);
        Double promedio = ratingRepository.promedioPuntuacionPorVendedor(email);
        int notificacionesNoLeidas = notificationRepository.countByUsuarioEmailAndLeidaFalse(email);

        if (promedio == null) {
            promedio = 0.0;
        }

        return new DashboardResumen(compras, ventas, productos, promedio, notificacionesNoLeidas);
    }
}
