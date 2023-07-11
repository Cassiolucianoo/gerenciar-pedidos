package com.cassio.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cassio.orderapi.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    // Outros métodos personalizados, se necessário
}
