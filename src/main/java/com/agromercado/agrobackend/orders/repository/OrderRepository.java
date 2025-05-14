package com.agromercado.agrobackend.orders.repository;

import com.agromercado.agrobackend.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    int countByCompradorEmail(String compradorEmail); // 👈 Agrega esta línea
}
