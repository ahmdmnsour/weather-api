package com.orange.weather.payload.response.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Current {
    @JsonProperty("temp_c")
    private double tempC;
    @JsonProperty("temp_f")
    private double tempF;
    private Condition condition;
}
