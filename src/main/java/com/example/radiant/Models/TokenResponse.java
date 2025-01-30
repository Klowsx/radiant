package com.example.radiant.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    @JsonProperty("access_token")
    String accesToken;

    @JsonProperty("refresh_token")
    String refreshToken;
}
