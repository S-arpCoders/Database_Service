package com.example.inventory.controller;

import com.example.inventory.model.Stock;
import com.example.inventory.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // Get all stocks
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    // Get stock by ID
    @GetMapping("/{stockId}")
    public ResponseEntity<Stock> getStockById(@PathVariable int stockId) {
        Optional<Stock> stock = stockService.getStockById(stockId);
        if (stock.isPresent()) {
            return new ResponseEntity<>(stock.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add new stock
    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.addStock(stock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    // Update an existing stock
    @PutMapping("/{stockId}")
    public ResponseEntity<Stock> updateStock(@PathVariable int stockId, @RequestBody Stock stock) {
        Optional<Stock> updatedStock = stockService.updateStock(stockId, stock);
        if (updatedStock.isPresent()) {
            return new ResponseEntity<>(updatedStock.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a stock
    @DeleteMapping("/{stockId}")
    public ResponseEntity<Void> deleteStock(@PathVariable int stockId) {
        boolean isDeleted = stockService.deleteStock(stockId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
