package com.orange.weather.security;

import com.orange.weather.entity.Role;
import com.orange.weather.security.jwt.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationFilter authenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(config -> config

                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/admins").hasAuthority(Role.ROLE_SUPER_ADMIN.name())
                .requestMatchers(HttpMethod.POST, "/api/admins").hasAuthority(Role.ROLE_SUPER_ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "/api/admins/**").hasAuthority(Role.ROLE_SUPER_ADMIN.name())
                .requestMatchers("/api/notes").hasAuthority(Role.ROLE_SUPER_ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/admins/**").hasAuthority(Role.ROLE_ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "/api/admins/**").hasAuthority(Role.ROLE_ADMIN.name())
                .requestMatchers("/api/notes/**").hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_SUPER_ADMIN.name())
                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAuthority(Role.ROLE_USER.name())
                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasAuthority(Role.ROLE_USER.name())
                .requestMatchers("/api/weather/**").hasAuthority(Role.ROLE_USER.name())
                .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}