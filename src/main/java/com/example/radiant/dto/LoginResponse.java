package com.example.radiant.dto;

import com.example.radiant.Models.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginResponse {
    private Long id;
    private String email;
    private String name;
    private String last_name;
    private Role role;

}
