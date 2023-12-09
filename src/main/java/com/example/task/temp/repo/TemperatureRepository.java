package com.example.task.temp.repo;

import com.example.task.temp.entity.TemperatureEntity;
import com.example.task.temp.view.TemperatureDetailedView;
import com.example.task.temp.view.TemperatureView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity, Long> {

    @Query("SELECT t1.deviceId as deviceId, t1.value as value\n" +
            "FROM Temperature t1\n" +
            "WHERE t1.creationDate = (\n" +
            "    SELECT MAX(t2.creationDate)\n" +
            "    FROM Temperature t2\n" +
            "    WHERE t1.deviceId = t2.deviceId)")
    List<TemperatureDetailedView> findLatestTemperature();

    List<TemperatureView> findByDeviceIdOrderByCreationDateDesc(Long id);
}
