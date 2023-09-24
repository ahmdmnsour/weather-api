package com.orange.weather.controller;
//
//import com.orange.weather.entity.Role;
//import com.orange.weather.exception.EmailAlreadyExistsException;
//import com.orange.weather.exception.ObjectNotFoundException;
//import com.orange.weather.response.MessageResponse;
//import com.orange.weather.request.UserLoginRequest;
//import com.orange.weather.request.UserSignupRequest;
//import com.orange.weather.response.UserInfoResponse;
//import com.orange.weather.security.jwt.JwtUtils;
//import com.orange.weather.security.AppUserDetails;
//import com.orange.weather.service.AuthService;
//import com.orange.weather.service.UserService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//    private final AuthService authService;
//    private final UserService userService;
//    private final JwtUtils jwtUtils;
//
//    @Autowired
//    public AuthController(AuthService authService, UserService userService, JwtUtils jwtUtils) {
//        this.authService = authService;
//        this.userService = userService;
//        this.jwtUtils = jwtUtils;
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginRequest loginRequest) throws ObjectNotFoundException {
//        AppUserDetails userDetails = (AppUserDetails) authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
//
//        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(new UserInfoResponse(userDetails.getUsername(),
//                        userDetails.getName(),
//                        userDetails.getRole().name()));
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignupRequest signUpRequest) throws EmailAlreadyExistsException, ObjectNotFoundException {
//        userService.createUser(signUpRequest, Role.ROLE_USER);
//
//        AppUserDetails userDetails = (AppUserDetails) authService.authenticateUser(signUpRequest.getEmail(), signUpRequest.getPassword());
//
//        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(new UserInfoResponse(userDetails.getUsername(),
//                        userDetails.getName(),
//                        userDetails.getRole().name()));
//    }
//
//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(new MessageResponse("You've been signed out!"));
//    }
//}

import com.orange.weather.exception.EmailAlreadyExistsException;
import com.orange.weather.payload.request.LoginRequest;
import com.orange.weather.payload.request.RegisterRequest;
import com.orange.weather.payload.response.LoginInfo;
import com.orange.weather.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(authService.authenticateUser(request));
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout() {
//        return ResponseEntity.ok(authService.logout());
//    }
}