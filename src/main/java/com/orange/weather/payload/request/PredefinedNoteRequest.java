package com.orange.weather.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PredefinedNoteRequest {
    @NotBlank
    private String note;

    @NotNull
    @JsonProperty("min_temp")
    private int minTemp;

    @NotNull
    @JsonProperty("max_temp")
    private int maxTemp;
}
