package com.example.task.temp.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record AddTemperaturesDto(
        @NotNull
        @Pattern(regexp = "^0x[0-9A-Fa-f]{10}([0-9A-Fa-f]{10})*$",
                message = "not valid")
        String data
) {
}
