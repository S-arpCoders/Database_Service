package com.example.inventory.controller;

import com.example.inventory.model.User;
import com.example.inventory.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE: Add a new user
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        HttpStatus status = userRepository.addUser(user);
        return new ResponseEntity<>(status);
    }

    // READ: Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // READ: Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE: Update user details
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        HttpStatus status = userRepository.updateUser(user);
        return new ResponseEntity<>(status);
    }

    // DELETE: Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        HttpStatus status = userRepository.deleteUser(id);
        return new ResponseEntity<>(status);
    }
}
