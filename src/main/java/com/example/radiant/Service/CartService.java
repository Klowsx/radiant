package com.example.radiant.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.radiant.Models.Cart;
import com.example.radiant.Models.CartDetail;
import com.example.radiant.Models.Product;
import com.example.radiant.Models.User;
import com.example.radiant.Repositories.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(new User(userId));
                    return cartRepository.save(newCart);
                });
    }

    public Cart addToCart(Long userId, Long productId, Integer quantity,
            BigDecimal unitPrice) {
        Cart cart = getCartByUser(userId);

        CartDetail detail = new CartDetail();
        detail.setCart(cart);
        detail.setProduct(new Product(productId));
        detail.setQuantity(quantity);
        detail.setUnit_price(unitPrice);

        cart.getDetails().add(detail);
        return cartRepository.save(cart);
    }

    public void deleteProductCart(Long userId, Long productId) {
        Cart cart = getCartByUser(userId);
        cart.getDetails().removeIf(detail -> detail.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }

    public void emptyCart(Long userId) {
        Cart cart = getCartByUser(userId);
        cart.getDetails().clear();
        cartRepository.save(cart);
    }
}
