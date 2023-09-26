package com.orange.weather.service;

import com.orange.weather.entity.Admin;
import com.orange.weather.entity.User;
import com.orange.weather.exception.ObjectNotFoundException;
import com.orange.weather.repository.UserRepository;
import com.orange.weather.payload.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) throws ObjectNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ObjectNotFoundException("user not found with id:" + id);
        }

        return user.get();
    }

    public User getUserDetails() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(user.getUsername()).get();
    }

//    public User getUserByEmail(String email) throws ObjectNotFoundException {
//        Optional<User> user = userRepository.findByEmail(email);
//        if (user.isEmpty()) {
//            throw new ObjectNotFoundException("user not found with email:" + email);
//        }
//
//        return user.get();
//    }
//
//    @Transactional
//    public UserInfoResponse createUser(UserSignupRequest request, Role role) throws EmailAlreadyExistsException {
//        if (userRepository.existsByEmail(request.getEmail())) {
//            throw new EmailAlreadyExistsException("email already exists");
//        }
//
//        User user = new User(request.getEmail(),
//                request.getName(),
//                encoder.encode(request.getPassword()),
//                role);
//
//        userRepository.save(user);
//
//        return new UserInfoResponse(user.getEmail(), user.getName(), user.getRole().name());
//    }

    @Transactional
    public User updateUser(UserUpdateRequest request) throws UsernameNotFoundException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).get();

        if (request.getName() != null)
            user.setName(request.getName());
        if (request.getPassword() != null)
            user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }
}
