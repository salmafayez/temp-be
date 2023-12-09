package com.example.task.temp.controller;

import com.example.task.temp.config.WebSocketHandler;
import com.example.task.temp.dto.AddTemperaturesDto;
import com.example.task.temp.entity.TemperatureEntity;
import com.example.task.temp.service.TemperatureService;
import com.example.task.temp.view.TemperatureDetailedView;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/temperatures")
@CrossOrigin(origins = "http://localhost:4200")
public class TemperatureController {

    private final TemperatureService temperatureService;

    private final WebSocketHandler webSocketHandler;

    @GetMapping
    public ResponseEntity<List<TemperatureDetailedView>> getLatestTemperatures () {
        return ResponseEntity.ok(temperatureService.getLatestTemperature());
    }

    @PostMapping
    public ResponseEntity<Void> addTemperatures (
            @RequestBody
            @Valid
            AddTemperaturesDto addTemperaturesDto) throws JsonProcessingException {
        List<TemperatureEntity> temperatureList = parseTemperatures(addTemperaturesDto.data());
        temperatureService.addTemperatures(temperatureList);
        webSocketHandler.updateSubscribers(temperatureList);
        return ResponseEntity.ok().build();
    }

    private List<TemperatureEntity> parseTemperatures(String data) {
        data = data.substring(2);
        List<TemperatureEntity> temperatureEntityList = new ArrayList<>();
        return parseTemperatures(data, temperatureEntityList);
    }

    private List<TemperatureEntity> parseTemperatures(String data, List<TemperatureEntity> temperatureList) {
        if(data.isEmpty()) return temperatureList;
        TemperatureEntity temperatureEntity = buildTemperature(data);
        temperatureList.add(temperatureEntity);
        return parseTemperatures(data.substring(10), temperatureList);
    }

    private static TemperatureEntity buildTemperature(String data) {
        Long deviceId = Long.parseLong(data.substring(0, 8), 16);
        Integer temperatureValue = Integer.parseInt(data.substring(8, 10), 16);
        return TemperatureEntity.builder()
                .deviceId(deviceId)
                .value(temperatureValue)
                .build();
    }
}
