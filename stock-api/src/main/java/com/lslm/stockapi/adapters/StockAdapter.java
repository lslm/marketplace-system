package com.lslm.stockapi.adapters;

import com.lslm.stockapi.adapters.requests.StockCreationRequest;
import com.lslm.stockapi.adapters.responses.StockCreationResponse;
import com.lslm.stockapi.entities.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockAdapter {
    public Stock toStock(StockCreationRequest stockCreationRequest) {
        return Stock.builder()
                .productId(stockCreationRequest.productId())
                .quantity(stockCreationRequest.quantity())
                .availableQuantity(stockCreationRequest.quantity())
                .build();
    }

    public StockCreationResponse toStockCreationResponse(Stock stock) {
        return StockCreationResponse.builder()
                .id(stock.getId())
                .productId(stock.getProductId())
                .quantity(stock.getQuantity())
                .availableQuantity(stock.getAvailableQuantity())
                .build();
    }
}
