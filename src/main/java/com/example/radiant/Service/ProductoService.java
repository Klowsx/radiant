package com.example.radiant.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.radiant.Models.ImagenProducto;
import com.example.radiant.Models.Producto;
import com.example.radiant.Repositories.ImagenProductoRepository;
import com.example.radiant.Repositories.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ImagenProductoRepository imagenProductoRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto actualizaProducto(Long id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setCategoria(producto.getCategoria());
        productoExistente.setEstado(producto.getEstado());
        productoExistente.setStock(producto.getStock());
        return productoRepository.save(productoExistente);
    }

    public ImagenProducto agregarImagenProducto(Long productoId, MultipartFile archivo) throws Exception {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        String rutaArchivo = fileStorageService.guardarArchivo(archivo, "products");

        ImagenProducto imagenProducto = new ImagenProducto();
        imagenProducto.setProducto(producto);
        imagenProducto.setRutaArchivo(rutaArchivo);

        return imagenProductoRepository.save(imagenProducto);
    }
}
