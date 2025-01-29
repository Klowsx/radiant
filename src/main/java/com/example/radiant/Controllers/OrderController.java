package com.example.radiant.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.radiant.Models.Order;
import com.example.radiant.Service.OrderService;

@RestController
@RequestMapping("/pedido")
public class OrderController {
    @Autowired
    private OrderService pedidoService;

    @GetMapping("/todos")
    public List<Order> getAllPedidos() {
        return pedidoService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.getOrderById(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Order> obtenerPedidosUsuario(@PathVariable Long usuarioId) {
        return pedidoService.getOrdersByUser(usuarioId);
    }

    @PatchMapping("/estado/{id}")
    public ResponseEntity<Order> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {

        Order pedidoActualizado = pedidoService.updateOrderStatus(id, estado);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
        pedidoService.deleteOrder(id);
        return ResponseEntity.ok("Pedido eliminado con exito");
    }

}
