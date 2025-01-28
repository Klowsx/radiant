package com.example.radiant.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Carrito;
import com.example.radiant.Models.DetalleCarrito;
import com.example.radiant.Models.Empresa;
import com.example.radiant.Models.Pedido;
import com.example.radiant.Models.DetallePedido;
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

    public List<Pedido> crearPedidoDesdeCarrito(Long usuarioId) {
        Carrito carrito = carritoService.obtenerCarritoPorUsuario(usuarioId);

        Map<Long, List<DetalleCarrito>> detallesPorEmpresa = carrito.getDetalles().stream()
                .collect(Collectors.groupingBy(detalle -> detalle.getProducto().getEmpresa().getId()));

        List<Pedido> pedidos = new ArrayList<>();

        for (Map.Entry<Long, List<DetalleCarrito>> entry : detallesPorEmpresa.entrySet()) {
            Pedido pedido = new Pedido();
            pedido.setUsuario(carrito.getUsuario());
            pedido.setEmpresa(new Empresa(entry.getKey()));
            pedido.setEstado("pendiente");
            pedido.setTotal(entry.getValue().stream()
                    .map(detalle -> detalle.getPrecioUnitario().multiply(BigDecimal.valueOf(detalle.getCantidad())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));

            pedido = pedidoRepository.save(pedido);

            for (DetalleCarrito detalleCarrito : entry.getValue()) {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setPedido(pedido);
                detallePedido.setProducto(detalleCarrito.getProducto());
                detallePedido.setCantidad(detalleCarrito.getCantidad());
                detallePedido.setPrecioUnitario(detalleCarrito.getPrecioUnitario());
                detallePedido.setSubtotal(
                        detalleCarrito.getPrecioUnitario().multiply(BigDecimal.valueOf(detalleCarrito.getCantidad())));
                detallePedido = detallePedidoRepository.save(detallePedido);
            }
            pedidos.add(pedido);

        }
        return pedidos;

    }

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
