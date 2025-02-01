package com.example.radiant.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartRequest {
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}
