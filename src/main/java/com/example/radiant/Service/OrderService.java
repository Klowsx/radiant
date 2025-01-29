package com.example.radiant.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Order;
import com.example.radiant.Repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Order> getOrdersByUser(Long usuarioId) {
        return orderRepository.findByUserId(usuarioId);
    }

    public Order updateOrderStatus(Long id, String newState) {
        List<String> validStates = Arrays.asList("pendiente", "procesado", "enviado");
        if (!validStates.contains(newState.toLowerCase())) {
            throw new IllegalArgumentException("Estado invalido: " + newState);
        }

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));

        order.setState(newState);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        orderRepository.delete(order);
    }
}
