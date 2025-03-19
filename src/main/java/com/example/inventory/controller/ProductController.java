package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public HttpStatus addProduct(@RequestBody Product product) {
        return productRepository.addProduct(product);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable int productId) {
        return productRepository.getProductById(productId);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @PutMapping("/{productId}")
    public HttpStatus updateProduct(@PathVariable int productId, @RequestBody Product product) {
        product.setProductId(productId); // Ensure we set the productId before updating
        return productRepository.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    public HttpStatus deleteProduct(@PathVariable int productId) {
        return productRepository.deleteProduct(productId);
    }
}
