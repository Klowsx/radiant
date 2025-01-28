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

import com.example.radiant.Models.Empresa;
import com.example.radiant.Models.Pedido;
import com.example.radiant.Service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear/{usuarioId}")
    public ResponseEntity<List<Pedido>> crearPedidoDesdeCarrito(@PathVariable Long usuarioId) {
        List<Pedido> pedidos = pedidoService.crearPedidoDesdeCarrito(usuarioId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/todos")
    public List<Pedido> getAllPedidos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Pedido> obtenerPedidosUsuario(@PathVariable Long usuarioId) {
        return pedidoService.obtenerPedidosPorUsuario(usuarioId);
    }

    @GetMapping("/empresa/{id}")
    public Optional<Empresa> obtenerPedidosPorEmpresa(@PathVariable Long id) {
        return pedidoService.obtenerPedidosPorEmpresa(id);
    }

    @PatchMapping("/estado/{id}")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {

        Pedido pedidoActualizado = pedidoService.actualizarEstadoPedido(id, estado);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.ok("Pedido eliminado con exito");
    }

}
