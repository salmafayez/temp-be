package com.example.task.temp.service;

import com.example.task.temp.entity.TemperatureEntity;
import com.example.task.temp.view.TemperatureDetailedView;
import com.example.task.temp.view.TemperatureView;

import java.util.List;

public interface TemperatureService {
    void addTemperatures(List<TemperatureEntity> temperatureList);

    List<TemperatureDetailedView> getLatestTemperature();

    List<TemperatureView> getTemperatureByDevice(Long id);
}
