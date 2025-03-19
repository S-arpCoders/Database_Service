package com.example.inventory.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getSalesReport() {
        String sql = "SELECT p.name AS product, SUM(s.quantity) AS total_sold, SUM(s.quantity * p.price) AS revenue " +
                     "FROM sales s JOIN products p ON s.product_id = p.product_id " +
                     "GROUP BY p.name";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getInventoryReport() {
        String sql = "SELECT p.name AS product, b.expiration_date, b.quantity " +
                     "FROM batches b JOIN products p ON b.product_id = p.product_id " +
                     "WHERE b.expiration_date <= CURDATE()";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getStockMovements() {
        String sql = "SELECT p.name AS product, s.movement_type, s.quantity, s.movement_date " +
                     "FROM stockmovements s JOIN products p ON s.product_id = p.product_id " +
                     "ORDER BY s.movement_date DESC";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getSupplierReport() {
        String sql = "SELECT s.name AS supplier, COUNT(p.purchase_id) AS total_purchases " +
                     "FROM purchases p JOIN suppliers s ON p.supplier_id = s.supplier_id " +
                     "GROUP BY s.name";
        return jdbcTemplate.queryForList(sql);
    }
}
