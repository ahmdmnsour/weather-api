package com.orange.weather.payload.response.weather;

import com.orange.weather.entity.Note;
import com.orange.weather.payload.response.NoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private Location location;
    private Current current;
    private List<NoteResponse> notes;
}