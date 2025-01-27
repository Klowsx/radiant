package com.example.radiant.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Empresa;
import com.example.radiant.Models.Pedido;
import com.example.radiant.Repositories.DetallePedidoRepository;
import com.example.radiant.Repositories.EmpresaRepository;
import com.example.radiant.Repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Pedido> obtenerPedidosPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Empresa> obtenerPedidosPorEmpresa(Long empresaId) {
        return empresaRepository.findById(empresaId);
    }

    public Pedido actualizarEstadoPedido(Long id, String nuevoEstado) {
        List<String> estadosValidos = Arrays.asList("pendiente", "procesado", "enviado");
        if (!estadosValidos.contains(nuevoEstado.toLowerCase())) {
            throw new IllegalArgumentException("Estado invalido: " + nuevoEstado);
        }

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));

        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedidoRepository.delete(pedido);
    }
}
