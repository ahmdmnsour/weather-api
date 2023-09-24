package com.orange.weather.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInfo {
    private String email;
    private String name;
    private String role;
    private String token;
}
