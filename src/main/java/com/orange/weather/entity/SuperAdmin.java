package com.orange.weather.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class SuperAdmin extends Admin{
    public SuperAdmin(String email, String name, String password, Role role) {
        super(email, name, password, role);
    }
}
