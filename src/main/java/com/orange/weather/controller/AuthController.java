package com.orange.weather.controller;

import com.orange.weather.exception.EmailAlreadyExistsException;
import com.orange.weather.payload.request.LoginRequest;
import com.orange.weather.payload.request.RegisterRequest;
import com.orange.weather.payload.response.LoginInfo;
import com.orange.weather.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<LoginInfo> register(@RequestBody RegisterRequest request) throws EmailAlreadyExistsException {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginInfo> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.authenticateUser(request));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


    }



//    @PostMapping("/logout")
//    public ResponseEntity<String> logout() {
//        return ResponseEntity.ok(authService.logout());
//    }
}