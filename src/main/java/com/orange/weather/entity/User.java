package com.orange.weather.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email, String name, String password, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(int id, String email, String name, String password, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

//    public User(String email, String name, String password, String role) {
//        this.email = email;
//        this.name = name;
//        this.password = password;
//        if (Objects.equals(role, "ROLE_SUPER_ADMIN"))
//            this.role = Role.ROLE_SUPER_ADMIN;
//        else if (Objects.equals(role, "ROLE_ADMIN"))
//            this.role = Role.ROLE_ADMIN;
//        else if (Objects.equals(role, "ROLE_USER"))
//            this.role = Role.ROLE_USER;
//    }
}
