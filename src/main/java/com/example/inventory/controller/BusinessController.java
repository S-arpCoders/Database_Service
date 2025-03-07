package com.example.inventory.controller;

import com.example.inventory.model.Business;
import com.example.inventory.repository.BusinessRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {
    private final BusinessRepository businessRepository;

    public BusinessController(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addBusiness(@RequestBody Business business) {
        HttpStatus status = businessRepository.addBusiness(business);
        return ResponseEntity.status(status).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Business>> getAllBusinesses() {
        List<Business> businesses = businessRepository.getAllBusiness();
        return ResponseEntity.ok(businesses);
    }
}
