package com.orange.weather.controller;

import com.orange.weather.payload.response.weather.WeatherData;
import com.orange.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherData> getWeather(@PathVariable String city) {
        return ResponseEntity.ok(weatherService.getWeatherData(city));
    }
}