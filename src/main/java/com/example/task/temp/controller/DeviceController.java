package com.example.task.temp.controller;

import com.example.task.temp.service.TemperatureService;
import com.example.task.temp.view.TemperatureView;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/devices")
@CrossOrigin(origins = "http://localhost:4200")
public class DeviceController {

    private final TemperatureService temperatureService;


    @GetMapping("/{id}/temperatures")
    public ResponseEntity<List<TemperatureView>> getTemperatures (
            @PathVariable
            @NotNull
            Long id) {
        return ResponseEntity.ok(temperatureService.getTemperatureByDevice(id));
    }
}
