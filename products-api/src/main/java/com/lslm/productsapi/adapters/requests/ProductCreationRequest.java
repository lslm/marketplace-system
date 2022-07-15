package com.lslm.productsapi.adapters.requests;

import java.math.BigDecimal;

public record ProductCreationRequest(String description,
                                     BigDecimal price)
{
}
