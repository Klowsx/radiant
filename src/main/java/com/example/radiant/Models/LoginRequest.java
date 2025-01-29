package com.example.radiant.Models;

public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String correo, String contrasena) {
        this.email = correo;
        this.password = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String correo) {
        this.email = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contrasena) {
        this.password = contrasena;
    }
}
