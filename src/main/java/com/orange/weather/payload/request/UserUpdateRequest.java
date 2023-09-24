package com.orange.weather.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 6)
    private String password;
}
