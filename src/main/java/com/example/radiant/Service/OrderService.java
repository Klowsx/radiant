package com.example.radiant.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Cart;
import com.example.radiant.Models.CartDetail;
import com.example.radiant.Models.Order;
import com.example.radiant.Models.OrderDetail;
import com.example.radiant.Repositories.OrderDetailRepository;
import com.example.radiant.Repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Order addOrder(Long userId) {
        Cart cart = cartService.getCartByUser(userId);

        if (cart.getDetails().isEmpty()) {
            throw new IllegalStateException("El carrito esta vacio");
        }
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setState("pendiente");

        BigDecimal total = BigDecimal.ZERO;
        List<OrderDetail> orderDetails = new ArrayList<>();

        for (CartDetail cartDetail : cart.getDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartDetail.getProduct());
            orderDetail.setQuantity(cartDetail.getQuantity());
            orderDetail.setUnit_price(cartDetail.getUnit_price());
            orderDetail.setSubtotal(orderDetail.getUnit_price()
                    .multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
            total = total.add(orderDetail.getSubtotal());
            orderDetails.add(orderDetail);
        }
        order.setTotal(total);
        order = orderRepository.save(order);

        for (OrderDetail detail : orderDetails) {
            orderDetailRepository.save(detail);
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Order> getOrdersByUser(Long usuarioId) {
        return orderRepository.findByUserId(usuarioId);
    }

    public Order updateOrderStatus(Long id, String newState) {
        List<String> validStates = Arrays.asList("pendiente", "procesado", "enviado");
        if (!validStates.contains(newState.toLowerCase())) {
            throw new IllegalArgumentException("Estado invalido: " + newState);
        }

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));

        order.setState(newState);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        orderRepository.delete(order);
    }
}
