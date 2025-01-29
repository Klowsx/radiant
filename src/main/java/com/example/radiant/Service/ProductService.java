package com.example.radiant.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.radiant.Models.ProductImage;
import com.example.radiant.Models.Product;
import com.example.radiant.Repositories.ProductImageRepository;
import com.example.radiant.Repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository ProductImageRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategoria(product.getCategoria());
        existingProduct.setState(product.getState());
        existingProduct.setStock(product.getStock());
        return productRepository.save(existingProduct);
    }

    private static final List<String> imageFormat = Arrays.asList("image/jpg", "image/png", "image/jpeg");

    public ProductImage addProductImage(Long productId, MultipartFile file) throws Exception {
        if (imageFormat.contains(file.getContentType())) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            String filePath = fileStorageService.saveFile(file, "products");

            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setFile_path(filePath);

            return ProductImageRepository.save(productImage);
        } else {
            throw new Exception("Formato no permitido.");
        }
    }

    public void deleteProductImage(Long imageId) throws Exception {
        ProductImage productImage = ProductImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        fileStorageService.deleteFile(productImage.getFile_path());

        ProductImageRepository.delete(productImage);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al eliminar, producto no encontrado"));

        productRepository.delete(product);
    }
}
