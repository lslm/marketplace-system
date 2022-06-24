package com.lslm.stockapi.adapters.responses;

import lombok.Builder;

import java.util.UUID;

@Builder
public record StockCreationResponse(
        UUID id,
        UUID productId,
        int quantity,
        int availableQuantity
) {
}
