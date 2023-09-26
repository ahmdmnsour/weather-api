package com.orange.weather.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Admin extends User {
    @JsonBackReference
    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    List<Note> notes;

    @OneToOne(mappedBy = "admin")
    PredefinedNote predefinedNote;

    public Admin(String email,
                 String name,
                 String password,
                 Role role) {
        super(email, name, password, role);
    }
}
