package com.orange.weather.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Admin extends User {
    @OneToMany(mappedBy = "admin")
    List<Note> notes;

    public Admin(String email,
                 String name,
                 String password,
                 Role role) {
        super(email, name, password, role);
    }
}
