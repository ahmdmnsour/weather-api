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
//
//        WeatherData data = new RestTemplate().getForObject(url, WeatherData.class);
////        String note = noteService.getTodayNote();
////
////        data.setNote();

//        RestTemplate restTemplate = new RestTemplate();
//        WeatherData data =
//        Map<String, Object> parsedData = JsonParserFactory.getJsonParser().parseMap(data);
//        System.out.println(parsedData.get("location").);

        //weatherData.setCity(parsedData.get("location"));
        WeatherData weatherData = new RestTemplate().getForObject(url, WeatherData.class);
        Note note = noteService.getTodayNote();
        weatherData.setNote(note.getNote());

        return weatherData;
    }
}
