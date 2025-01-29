package com.example.radiant.Models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Transaction> transaction;

    private BigDecimal total;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User usuario) {
        this.user = usuario;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> detallesPedido) {
        this.orderDetail = detallesPedido;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transacciones) {
        this.transaction = transacciones;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String estado) {
        this.state = estado;
    }
}