package com.cassio.orderapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cassio.orderapi.model.Order;
import com.cassio.orderapi.service.OrderService;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public @ResponseBody Order novoProdutos(@Valid Order order) {
    orderService.createOrder(order); return order;
    
    }
    
    public @ResponseBody ResponseEntity<Order> createOrder(@Valid  Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.created(URI.create("/order/" + createdOrder.getId())).body(createdOrder);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
//        Order updatedOrder = orderService.updateOrder(id, order);
//        if (updatedOrder != null) {
//            return ResponseEntity.ok(updatedOrder);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.deleteOrder(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
