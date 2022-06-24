package com.lslm.stockapi.controllers;

import com.lslm.stockapi.adapters.StockAdapter;
import com.lslm.stockapi.adapters.requests.StockCreationRequest;
import com.lslm.stockapi.adapters.responses.StockCreationResponse;
import com.lslm.stockapi.entities.ProductStock;
import com.lslm.stockapi.entities.Stock;
import com.lslm.stockapi.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private StockAdapter stockAdapter;

    @PostMapping()
    public ResponseEntity<StockCreationResponse> create(@RequestBody StockCreationRequest stockCreationRequest) throws IOException {
        Stock newStock = stockAdapter.toStock(stockCreationRequest);
        Stock createdStock = stockService.create(newStock);

        if (createdStock != null)
            return new ResponseEntity<>(stockAdapter.toStockCreationResponse(createdStock), HttpStatus.CREATED);

        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Stock could not be created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> find(@PathVariable UUID id) {
        Stock stock = stockService.find(id);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Stock>> findAll() {
        List<Stock> stocks = stockService.findAll();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/available")
    public ResponseEntity<ProductStock> getByProduct(@PathVariable UUID productId) {
        ProductStock productStock = stockService.byProduct(productId);
        return new ResponseEntity<>(productStock, HttpStatus.OK);
    }
}
