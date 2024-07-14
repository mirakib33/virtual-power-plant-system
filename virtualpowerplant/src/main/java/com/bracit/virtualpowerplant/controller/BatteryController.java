package com.bracit.virtualpowerplant.controller;

import com.bracit.virtualpowerplant.model.Battery;
import com.bracit.virtualpowerplant.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/batteries")
@Validated
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public ResponseEntity<List<Battery>> addBatteries(@Validated @RequestBody List<Battery> batteries) {
        List<Battery> savedBatteries = batteryService.saveAllBatteries(batteries);
        return ResponseEntity.ok(savedBatteries);
    }

    @GetMapping
    public ResponseEntity<List<String>> getBatteriesInRange(@RequestParam String postcodeRange) {
        String[] range = postcodeRange.split("-");
        List<Battery> batteries = batteryService.getBatteriesByPostcodeRange(range[0], range[1]);

        List<String> batteryNames = batteries.stream()
                .map(Battery::getName)
                .sorted()
                .collect(Collectors.toList());

        return ResponseEntity.ok(batteryNames);
    }

}
