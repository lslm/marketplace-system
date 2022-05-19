package com.lslm.stockapi.controllers;

import com.lslm.stockapi.entities.Stock;
import com.lslm.stockapi.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping()
    public ResponseEntity<Stock> create(@RequestBody Stock newStock) {
        Stock stock = stockService.create(newStock);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
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
}
