package com.agromercado.agrobackend.catalog.repository;

import com.agromercado.agrobackend.catalog.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    int countByVendedorEmail(String vendedorEmail); // 👈 Agrega esta línea
}
