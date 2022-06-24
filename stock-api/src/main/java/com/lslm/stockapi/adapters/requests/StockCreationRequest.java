package com.lslm.stockapi.adapters.requests;

import java.util.UUID;

public record StockCreationRequest(
        UUID productId,
        int quantity
) {
}
