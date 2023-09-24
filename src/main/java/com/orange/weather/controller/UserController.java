package com.orange.weather.controller;

import com.orange.weather.entity.User;
import com.orange.weather.exception.ObjectNotFoundException;
import com.orange.weather.payload.request.UserUpdateRequest;
import com.orange.weather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws ObjectNotFoundException {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserUpdateRequest request) throws ObjectNotFoundException {
        return ResponseEntity.ok().body(userService.updateUser(id, request));
    }
}
