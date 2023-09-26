package com.orange.weather.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class SuperAdmin extends Admin {
    public SuperAdmin(String email, String name, String password, Role role) {
        super(email, name, password, role);
    }
}
