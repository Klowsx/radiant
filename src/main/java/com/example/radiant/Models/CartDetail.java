package com.example.radiant.Models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles_carrito")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Product product;

    private Integer quantity;

    @Column(name = "precio_unitario")
    private BigDecimal unit_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart carrito) {
        this.cart = carrito;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product producto) {
        this.product = producto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer cantidad) {
        this.quantity = cantidad;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal precioUnitario) {
        this.unit_price = precioUnitario;
    }
}
