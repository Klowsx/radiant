package com.example.radiant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.radiant.Config.JwtUtil;
import com.example.radiant.Models.AuthResponse;
import com.example.radiant.Models.LoginRequest;
import com.example.radiant.Models.RegisterRequest;
import com.example.radiant.Models.Role;
import com.example.radiant.Models.User;
import com.example.radiant.Repositories.AuthRepository;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Registro de los usuarios
    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya esta registrado");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());

        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser);
        return new AuthResponse(token, savedUser);
    }

    // Login
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Credenciales invalidas");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("El usuario no esta registrado"));

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user);
    }
}
