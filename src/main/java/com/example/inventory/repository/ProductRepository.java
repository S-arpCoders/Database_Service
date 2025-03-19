package com.example.inventory.repository;

import com.example.inventory.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public HttpStatus addProduct(Product product) {
        String sql = "INSERT INTO products (name, description, category_id, price, stock_quantity, barcode, warehouse_id) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, product.getProductId(), product.getName(), product.getDescription(),
                    product.getCategoryId(), product.getPrice(), product.getStockQuantity(),
                    product.getBarcode(), product.getWarehouseId());
            return HttpStatus.CREATED; // HTTP 201 Created
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setBarcode(rs.getString("barcode"));
                product.setWarehouseId(rs.getInt("warehouse_id"));
                return product;
            }, productId);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // If not found, return null
        }
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        try {
            return jdbcTemplate.query(sql, new RowMapper<Product>() {
                @Override
                public Product mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    Product product = new Product();
                    product.setProductId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStockQuantity(rs.getInt("stock_quantity"));
                    product.setBarcode(rs.getString("barcode"));
                    product.setWarehouseId(rs.getInt("warehouse_id"));
                    return product;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if any error occurs
        }
    }
    public HttpStatus updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, category_id = ?, price = ?, " +
                "stock_quantity = ?, barcode = ?, warehouse_id = ? WHERE product_id = ?";
        try {
            int updatedRows = jdbcTemplate.update(sql, product.getName(), product.getDescription(),
                    product.getCategoryId(), product.getPrice(),
                    product.getStockQuantity(), product.getBarcode(),
                    product.getWarehouseId(), product.getProductId());
            if (updatedRows > 0) {
                return HttpStatus.OK; // HTTP 200 OK
            }
            return HttpStatus.NOT_FOUND; // HTTP 404 Not Found if no rows were updated
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR; // HTTP 500 Internal Server Error
        }
    }

    public HttpStatus deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try {
            int deletedRows = jdbcTemplate.update(sql, productId);
            if (deletedRows > 0) {
                return HttpStatus.NO_CONTENT; // HTTP 204 No Content if deleted successfully
            }
            return HttpStatus.NOT_FOUND; // HTTP 404 Not Found if product doesn't exist
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR; // HTTP 500 Internal Server Error
        }
    }
}
