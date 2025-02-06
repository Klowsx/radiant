package com.example.radiant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.radiant.Config.JwtUtil;
import com.example.radiant.Models.Role;
import com.example.radiant.Models.User;
import com.example.radiant.Repositories.AuthRepository;
import com.example.radiant.dto.AuthResponse;
import com.example.radiant.dto.LoginRequest;
import com.example.radiant.dto.LoginResponse;
import com.example.radiant.dto.RegisterRequest;

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
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());

        User savedUser = userRepository.save(user);
        String token = jwtUtil.generateToken(savedUser);
        return new AuthResponse(token, savedUser);
    }

    // Login
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("El usuario no está registrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales invalidas");
        }
        String token = jwtUtil.generateToken(user);

        LoginResponse infoUser = new LoginResponse(
                user.getId(),
                user.getName(),
                user.getLast_name(),
                user.getEmail(),
                user.getRole());

        // Devolver la respuesta con el token y el usuario
        return new AuthResponse(token, infoUser);
    }

}
