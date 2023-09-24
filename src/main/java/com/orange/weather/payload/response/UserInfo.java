package com.orange.weather.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String email;
    private String name;
    private String role;
}
