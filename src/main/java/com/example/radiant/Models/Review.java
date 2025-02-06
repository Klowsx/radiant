package com.example.radiant.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Integer rating;
    private String comment;

    private LocalDateTime created_on;
    private LocalDateTime updated_on;

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

    public User getUser() {
        return user;
    }

    public void setUser(User usuario) {
        this.user = usuario;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer calificacion) {
        this.rating = calificacion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comentario) {
        this.comment = comentario;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime creadoEn) {
        this.created_on = creadoEn;
    }

    public LocalDateTime getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(LocalDateTime actualizadoEn) {
        this.updated_on = actualizadoEn;
    }

}
