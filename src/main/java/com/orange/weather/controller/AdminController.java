package com.orange.weather.controller;

import com.orange.weather.entity.Admin;
import com.orange.weather.exception.EmailAlreadyExistsException;
import com.orange.weather.exception.ObjectNotFoundException;
import com.orange.weather.payload.request.RegisterRequest;
import com.orange.weather.payload.request.UserUpdateRequest;
import com.orange.weather.payload.response.UserInfo;
import com.orange.weather.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok().body(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable int id) throws ObjectNotFoundException {
        return ResponseEntity.ok().body(adminService.getAdminById(id));
    }

    @PostMapping
    public ResponseEntity<UserInfo> createAdmin(@RequestBody RegisterRequest request) throws EmailAlreadyExistsException {
        return ResponseEntity.ok().body(adminService.createAdmin(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody UserUpdateRequest request) throws ObjectNotFoundException {
        return ResponseEntity.ok().body(adminService.updateAdmin(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable int id) throws ObjectNotFoundException {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().body("Success");
    }
}
