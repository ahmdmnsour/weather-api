package com.orange.weather.payload.response.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private Location location;
    private Current current;
    private String note;
}
