package com.example.radiant.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.radiant.Models.Pedido;
import com.example.radiant.Models.Usuario;
import com.example.radiant.Repositories.PedidoRepository;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;

    public Pedido crearPedido(Pedido pedido) {
        pedido.setEstado("pendiente");
        return pedidoRepository.save(pedido);
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuario) {

    }
}
