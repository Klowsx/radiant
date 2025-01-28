package com.example.radiant.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Carrito;
import com.example.radiant.Models.DetalleCarrito;
import com.example.radiant.Models.Producto;
import com.example.radiant.Models.Usuario;
import com.example.radiant.Repositories.CarritoRepository;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public Carrito obtenerCarritoPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(new Usuario(usuarioId));
                    return carritoRepository.save(nuevoCarrito);
                });
    }

    public Carrito agregarProductosAlCarrito(Long usuarioId, Long productoId, Integer cantidad,
            BigDecimal precioUnitario) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);

        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCarrito(carrito);
        detalle.setProducto(new Producto(productoId));
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precioUnitario);

        carrito.getDetalles().add(detalle);
        return carritoRepository.save(carrito);
    }

    public void eliminarProductoCarrito(Long usuarioId, Long productoId) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);
        carrito.getDetalles().removeIf(detalle -> detalle.getProducto().getId().equals(productoId));
        carritoRepository.save(carrito);
    }

    public void vaciarCarrito(Long usuarioId) {
        Carrito carrito = obtenerCarritoPorUsuario(usuarioId);
        carrito.getDetalles().clear();
        carritoRepository.save(carrito);
    }
}
