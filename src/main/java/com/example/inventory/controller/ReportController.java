package com.example.inventory.controller;

import com.example.inventory.repository.ReportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportRepository reportRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Map<String, Object>>> getSalesReport() {
        return ResponseEntity.ok(reportRepository.getSalesReport());
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<Map<String, Object>>> getInventoryReport() {
        return ResponseEntity.ok(reportRepository.getInventoryReport());
    }

    @GetMapping("/stock-movements")
    public ResponseEntity<List<Map<String, Object>>> getStockMovements() {
        return ResponseEntity.ok(reportRepository.getStockMovements());
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<Map<String, Object>>> getSupplierReport() {
        return ResponseEntity.ok(reportRepository.getSupplierReport());
    }
}
