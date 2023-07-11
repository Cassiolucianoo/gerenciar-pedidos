package com.cassio.orderapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cassio.orderapi.model.Order;
import com.cassio.orderapi.repository.OrderRepository;
import com.cassio.userapi.model.User;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        order.setCreated_at(LocalDateTime.now());
        order.setUpdated_at(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            existingOrder.setItem_description(updatedOrder.getItem_description());
            existingOrder.setItem_quantity(updatedOrder.getItem_quantity());
            existingOrder.setItem_quantity(updatedOrder.getItem_quantity());
            existingOrder.setTotal_value(updatedOrder.getTotal_value());
            existingOrder.setUpdated_at(LocalDateTime.now());
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public User getUserById(Long userId) {
        String userApiUrl = "http://localhost:8080/users/" + userId; // Substitua pela URL correta do serviço de usuários
        User user = restTemplate.getForObject(userApiUrl, User.class);
        return user;
    }
}

