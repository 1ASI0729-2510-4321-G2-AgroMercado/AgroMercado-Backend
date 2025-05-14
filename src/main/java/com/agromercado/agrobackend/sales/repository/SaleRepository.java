package com.agromercado.agrobackend.sales.repository;

import com.agromercado.agrobackend.sales.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByVendedorEmail(String vendedorEmail);
    int countByVendedorEmail(String vendedorEmail);
}
