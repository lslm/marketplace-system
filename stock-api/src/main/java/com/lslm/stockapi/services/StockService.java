package com.lslm.stockapi.services;

import com.lslm.stockapi.entities.Stock;
import com.lslm.stockapi.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock find(UUID id) {
        return stockRepository.findById(id).orElseThrow();
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
}
