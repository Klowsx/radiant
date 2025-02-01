package com.example.radiant.Controllers;

import com.example.radiant.Models.Cart;
import com.example.radiant.Models.User;
import com.example.radiant.Service.CartService;
import com.example.radiant.dto.CartRequest;
import com.example.radiant.Config.JwtUtil;
import com.example.radiant.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

        @Autowired
        private CartService cartService;

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private UserRepository userRepository;

        // Obtiene el carrito del usuario autenticado
        @GetMapping
        public ResponseEntity<Cart> getCart() {
                // Obtén el nombre de usuario del contexto de seguridad
                Long userId = Long.parseLong(
                                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                                                .getUsername());
                Cart cart = cartService.getCartByUser(userId);
                return ResponseEntity.ok(cart);
        }

        // Método para agregar un producto al carrito
        @PostMapping("/add")
        public ResponseEntity<Cart> addToCart(@RequestBody CartRequest cartRequest) {
                Long userId = Long.parseLong(
                                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                                                .getUsername());
                Cart cart = cartService.addToCart(userId, cartRequest);
                return ResponseEntity.ok(cart);
        }

        // Método para obtener el carrito del usuario
        @GetMapping("/")
        public ResponseEntity<Cart> getCart(@RequestHeader("Authorization") String token) {
                Long userId = Long.parseLong(
                                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                                                .getUsername());
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                Cart cart = cartService.getCartByUser(user.getId());
                return ResponseEntity.ok(cart);
        }

        // Método para eliminar un producto del carrito
        @DeleteMapping("/remove")
        public ResponseEntity<Void> removeProductFromCart(
                        @RequestHeader("Authorization") String token,
                        @RequestParam Long productId) {

                Long userId = Long.parseLong(
                                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                                                .getUsername());
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                cartService.deleteProductCart(user.getId(), productId);
                return ResponseEntity.noContent().build();
        }

        // Método para vaciar el carrito
        @DeleteMapping("/empty")
        public ResponseEntity<Void> emptyCart(@RequestHeader("Authorization") String token) {
                Long userId = Long.parseLong(
                                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                                                .getUsername());
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                cartService.emptyCart(user.getId());
                return ResponseEntity.noContent().build();
        }
}
