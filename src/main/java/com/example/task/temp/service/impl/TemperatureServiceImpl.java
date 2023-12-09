package com.example.task.temp.service.impl;

import com.example.task.temp.entity.TemperatureEntity;
import com.example.task.temp.repo.TemperatureRepository;
import com.example.task.temp.service.TemperatureService;
import com.example.task.temp.view.TemperatureDetailedView;
import com.example.task.temp.view.TemperatureView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;

    @Override
    public void addTemperatures(List<TemperatureEntity> temperatureList) {
        temperatureRepository.saveAll(temperatureList);
    }

    @Override
    public List<TemperatureDetailedView> getLatestTemperature() {
        return temperatureRepository.findLatestTemperature();
    }

    @Override
    public List<TemperatureView> getTemperatureByDevice(Long id) {
        return temperatureRepository.findByDeviceIdOrderByCreationDateDesc(id);
    }
}
