package com.orange.weather.service;

import com.orange.weather.entity.Role;
import com.orange.weather.entity.User;
import com.orange.weather.exception.EmailAlreadyExistsException;
import com.orange.weather.repository.UserRepository;
import com.orange.weather.payload.request.LoginRequest;
import com.orange.weather.payload.request.RegisterRequest;
import com.orange.weather.payload.response.LoginInfo;
import com.orange.weather.security.jwt.JWTService;
import com.orange.weather.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginInfo registerUser(RegisterRequest request) throws EmailAlreadyExistsException {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new EmailAlreadyExistsException("email already exist");

        User user = new User(
                request.getEmail(),
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                Role.ROLE_USER);

        userRepository.save(user);

        UserDetails userDetails = AppUserDetails.build(user);

        String jwt = jwtService.generateToken(userDetails);

        return new LoginInfo(user.getEmail(), user.getName(), user.getRole().name(), jwt);
    }

    public LoginInfo authenticateUser(LoginRequest request)  throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        Optional<User> user = userRepository.findByEmail(request.getEmail());
        UserDetails userDetails = AppUserDetails.build(user.get());

        String jwt = jwtService.generateToken(userDetails);

        return new LoginInfo(user.get().getEmail(), user.get().getName(), user.get().getRole().name(), jwt);
    }
}
