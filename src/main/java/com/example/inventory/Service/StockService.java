package com.example.inventory.service;

import com.example.inventory.model.Stock;
import com.example.inventory.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // Add new stock
    public HttpStatus addStock(Stock stock) {
        return stockRepository.addStock(stock);
    }

    // Get all stocks
    public List<Stock> getAllStocks() {
        return stockRepository.getAllStocks();
    }

    // Get stock by ID
    public Stock getStockById(int stockId) {
        return stockRepository.getStockById(stockId);
    }

    // Update stock
    public HttpStatus updateStock(int stockId, Stock stock) {
        return stockRepository.updateStock(stockId, stock);
    }

    // Delete stock
    public HttpStatus deleteStock(int stockId) {
        return stockRepository.deleteStock(stockId);
    }
}
