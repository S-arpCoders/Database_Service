package com.example.inventory.repository;


import com.example.inventory.model.Stock;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockRepository {
    private final JdbcTemplate jdbcTemplate;

    public StockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add new stock
    public HttpStatus addStock(Stock stock) {
        String sql = "INSERT INTO stock (stock_id, business_id, item_name, quantity, expiry_date) VALUES (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,
                    stock.getStockId(),
                    stock.getBusinessId(),
                    stock.getItemName(),
                    stock.getQuantity(),
                    stock.getExpiryDate());
            return HttpStatus.CREATED;
        } catch (DataAccessException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    // Get all stocks
    public List<Stock> getAllStocks() {
        String sql = "SELECT * FROM stock";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Stock stock = new Stock();
            stock.setStockId(rs.getInt("stock_id"));
            stock.setBusinessId(rs.getInt("business_id"));
            stock.setItemName(rs.getString("item_name"));
            stock.setQuantity(rs.getInt("quantity"));
            stock.setExpiryDate(rs.getString("expiry_date"));
            return stock;
        });
    }

    // Get stock by ID
    public Stock getStockById(int stockId) {
        String sql = "SELECT * FROM stock WHERE stock_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Stock stock = new Stock();
            stock.setStockId(rs.getInt("stock_id"));
            stock.setBusinessId(rs.getInt("business_id"));
            stock.setItemName(rs.getString("item_name"));
            stock.setQuantity(rs.getInt("quantity"));
            stock.setExpiryDate(rs.getString("expiry_date"));
            return stock;
        }, stockId);
    }

    // Update an existing stock
    public HttpStatus updateStock(int stockId, Stock stock) {
        String sql = "UPDATE stock SET business_id = ?, item_name = ?, quantity = ?, expiry_date = ? WHERE stock_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    stock.getBusinessId(),
                    stock.getItemName(),
                    stock.getQuantity(),
                    stock.getExpiryDate(),
                    stockId);
            if (rowsAffected > 0) {
                return HttpStatus.OK;
            }
            return HttpStatus.NOT_FOUND;
        } catch (DataAccessException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    // Delete stock by ID
    public HttpStatus deleteStock(int stockId) {
        String sql = "DELETE FROM stock WHERE stock_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, stockId);
            if (rowsAffected > 0) {
                return HttpStatus.NO_CONTENT;
            }
            return HttpStatus.NOT_FOUND;
        } catch (DataAccessException e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
 {
    
}
