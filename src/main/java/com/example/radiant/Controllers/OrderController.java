package com.example.radiant.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.radiant.Models.Order;
import com.example.radiant.Models.User;
import com.example.radiant.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private User user;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{usuarioId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getOrdersByUser(@PathVariable Long usuarioId) {
        return orderService.getOrdersByUser(usuarioId);
    }

    @PatchMapping("/state/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam String estado) {

        Order updatedOrder = orderService.updateOrderStatus(id, estado);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id, Authentication authentication) {
        Order order = orderService.getOrderById(id);

        Long loggedUser = Long.parseLong(authentication.getName());
        System.out.println("USERNAME DEL USUARIO CONECTADO: " + loggedUser);

        if (order.getUser().getId().equals(loggedUser)) {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Pedido eliminado");
        } else {
            return ResponseEntity.badRequest().body("No tienes permiso para eliminar este pedido");
        }
    }

}
