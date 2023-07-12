package com.cassio.orderapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order createOrder(Order order) {
        // Set created_at and updated_at timestamps
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            // Update the order's attributes
            existingOrder.setItemDescription(order.getItemDescription());
            existingOrder.setItemQuantity(order.getItemQuantity());
            existingOrder.setItemPrice(order.getItemPrice());
            existingOrder.setTotalValue(order.getTotalValue());
            existingOrder.setUpdatedAt(LocalDateTime.now());

            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            // Make a REST API call to User-API to get the user details
            String userApiUrl = "http://localhost:8080/users/" + order.getUserId();
            ResponseEntity<User> response = restTemplate.getForEntity(userApiUrl, User.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
        }
        return null;
    }
}
