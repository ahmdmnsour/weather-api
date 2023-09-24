package com.orange.weather.security;

import com.orange.weather.entity.Role;
import com.orange.weather.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
public class AppUserDetails implements UserDetails {
  private int id;
  private String email;
  private String name;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;


  public AppUserDetails(int id, String email, String name, String password, Role role) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.password = password;
    this.role = role;
  }

  public static AppUserDetails build(User user) {
    return new AppUserDetails(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.getPassword(),
            user.getRole());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.role.name()));
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AppUserDetails user = (AppUserDetails) o;
    return Objects.equals(id, user.id);
  }
}
