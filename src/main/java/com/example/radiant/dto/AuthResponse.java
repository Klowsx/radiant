package com.example.radiant.dto;

import com.example.radiant.Models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private User user;
    private LoginResponse loginResponse;

    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public AuthResponse(String token, LoginResponse loginResponse) {
        this.token = token;
        this.loginResponse = loginResponse;
    }

}
