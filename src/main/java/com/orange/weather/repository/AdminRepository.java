package com.orange.weather.repository;

import com.orange.weather.entity.Admin;
import com.orange.weather.entity.Role;
import com.orange.weather.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<Admin> findAllByRole(Role role);
}
