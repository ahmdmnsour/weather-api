package com.orange.weather.repository;

import com.orange.weather.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer> {
}
