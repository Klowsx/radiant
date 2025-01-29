package com.example.radiant.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.radiant.Models.ProductImage;
import com.example.radiant.Models.Product;
import com.example.radiant.Service.ProductService;

@RestController
@RequestMapping("/producto")
public class ProductController {
    @Autowired
    private ProductService productoService;

    @GetMapping("/todos")
    public List<Product> obtenerTodosLosProductos() {
        return productoService.getAllProducts();
    }

    @PostMapping("/crear")
    public ResponseEntity<Product> crearProducto(@RequestBody Product producto) {
        Product nuevoProducto = productoService.addProduct(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Product> producto = productoService.getProductById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Product> actualizarProducto(@PathVariable Long id, @RequestBody Product producto) {
        Product productoActualizado = productoService.updateProduct(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/agregar-imagen")
    public ResponseEntity<ProductImage> agregarImagenProducto(
            @PathVariable Long id,
            @RequestParam("archivo") MultipartFile archivo) {
        try {
            ProductImage imagenProducto = productoService.addProductImage(id, archivo);
            return ResponseEntity.ok(imagenProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/imagenes/{imagenId}")
    public ResponseEntity<String> eliminarImagenProducto(@PathVariable Long imagenId) {
        try {
            productoService.deleteProductImage(imagenId);
            return ResponseEntity.ok("Imagen eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al borrar la imagen: " + e.getMessage());
        }
    }
}
