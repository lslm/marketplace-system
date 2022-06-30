package com.lslm.marketplacebff.adapters.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationResponse {
    private UUID id;
    private String description;
    private BigDecimal price;
}
