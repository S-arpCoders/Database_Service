package com.example.inventory.controller;

import com.example.inventory.model.User;
import com.example.inventory.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus>  addUser(@RequestBody User user) {
        HttpStatus status= userRepository.addUser(user);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
