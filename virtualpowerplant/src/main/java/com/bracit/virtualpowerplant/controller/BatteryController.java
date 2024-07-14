package com.bracit.virtualpowerplant.controller;

import com.bracit.virtualpowerplant.model.Battery;
import com.bracit.virtualpowerplant.model.BatteryStatistics;
import com.bracit.virtualpowerplant.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/batteries")
@Validated
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public ResponseEntity<List<Battery>> addBatteries( @RequestBody List<Battery> batteries) {
        List<Battery> savedBatteries = batteryService.saveAllBatteries(batteries);
        return ResponseEntity.ok(savedBatteries);
    }

    @GetMapping
    public ResponseEntity<?> getBatteriesInRange(@RequestParam String postcodeRange) {
        String[] range = postcodeRange.split("-");
        List<Battery> batteries = batteryService.getBatteriesByPostcodeRange(range[0], range[1]);

        List<String> batteryNames = batteries.stream()
                .map(Battery::getName)
                .sorted()
                .collect(Collectors.toList());

        int totalWattCapacity = batteries.stream().mapToInt(Battery::getWattCapacity).sum();
        double averageWattCapacity = batteries.stream().mapToInt(Battery::getWattCapacity).average().orElse(0);

        return ResponseEntity.ok(new BatteryStatistics(batteryNames, totalWattCapacity, averageWattCapacity));
    }

}
