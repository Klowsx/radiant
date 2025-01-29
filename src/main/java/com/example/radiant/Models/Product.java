package com.example.radiant.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String state;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category categoria;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    public Product(Long productoId) {
        this.id = productoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double precio) {
        this.price = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getState() {
        return state;
    }

    public void setState(String estado) {
        this.state = estado;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date creadoEn) {
        this.created_on = creadoEn;
    }

    public Date getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(Date actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
