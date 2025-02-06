package com.example.radiant.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String file_path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product producto) {
        this.product = producto;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String urlImagen) {
        this.file_path = urlImagen;
    }
}
