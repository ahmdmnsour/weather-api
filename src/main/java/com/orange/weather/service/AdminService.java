package com.orange.weather.service;

import com.orange.weather.entity.Admin;
import com.orange.weather.entity.Role;
import com.orange.weather.exception.EmailAlreadyExistsException;
import com.orange.weather.repository.AdminRepository;
import com.orange.weather.payload.request.RegisterRequest;
import com.orange.weather.payload.request.UserUpdateRequest;
import com.orange.weather.payload.response.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty())
            throw new UsernameNotFoundException("admin not found with id:" + id);

        return admin.get();
    }

    @Transactional
    public UserInfo createAdmin(RegisterRequest request) throws EmailAlreadyExistsException {
        if (adminRepository.existsByEmail(request.getEmail()))
            throw new EmailAlreadyExistsException("email already exists");

        Admin admin = new Admin(request.getEmail(),
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                Role.ROLE_ADMIN);

        adminRepository.save(admin);

        return new UserInfo(admin.getEmail(), admin.getName(), admin.getRole().name());
    }

    @Transactional
    public Admin updateAdmin(int id, UserUpdateRequest request) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty())
            throw new UsernameNotFoundException("admin not found with id:" + id);

        admin.get().setName(request.getName());
        admin.get().setPassword(request.getPassword());

        return adminRepository.save(admin.get());
    }

    @Transactional
    public void deleteAdmin(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty())
            throw new UsernameNotFoundException("admin not found with id:" + id);

        adminRepository.delete(admin.get());
    }
}
