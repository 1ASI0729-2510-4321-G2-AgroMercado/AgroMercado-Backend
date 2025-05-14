package com.agromercado.agrobackend.sales.service;

import com.agromercado.agrobackend.sales.model.Sale;
import com.agromercado.agrobackend.sales.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Sale registrarVenta(Sale sale) {
        sale.setFechaVenta(LocalDateTime.now());
        return saleRepository.save(sale);
    }

    public List<Sale> listarVentas() {
        return saleRepository.findAll();
    }

    public Optional<Sale> obtenerVentaPorId(Long id) {
        return saleRepository.findById(id);
    }

    public List<Sale> listarVentasPorVendedor(String vendedorEmail) {
        return saleRepository.findByVendedorEmail(vendedorEmail);
    }

    public boolean eliminarVenta(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
