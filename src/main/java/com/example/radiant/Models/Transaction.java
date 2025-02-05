package com.example.radiant.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private String payment_method;
    private String status; // 'completado', 'fallido', 'pendiente'

    private LocalDateTime created_on;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order pedido) {
        this.order = pedido;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String metodoPago) {
        this.payment_method = metodoPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String estado) {
        this.status = estado;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime creadoEn) {
        this.created_on = creadoEn;
    }
}
