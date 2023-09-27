package com.orange.weather.service;

import com.orange.weather.entity.Note;
import com.orange.weather.payload.response.weather.WeatherData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private static final String API_KEY = "005e653ed0dc4d35ac7192041200411";
    private static final String BASE_URL = "http://api.weatherapi.com/v1/current.json";

    private final NoteService noteService;

    public WeatherData getWeatherData(String city) {
        String url = BASE_URL + "?key=" + API_KEY + "&q=" + city + "&aqi=no";
;
        WeatherData weatherData = new RestTemplate().getForObject(url, WeatherData.class);
        weatherData.setNotes(noteService.getTodayNotes());

        return weatherData;
    }
}
