package com.bracit.virtualpowerplant.service.impl;

import com.bracit.virtualpowerplant.model.Battery;
import com.bracit.virtualpowerplant.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BatteryServiceImpl {

    @Autowired
    private BatteryRepository batteryRepository;

    public Battery saveBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    public List<Battery> saveAllBatteries(List<Battery> batteries) {
        return batteryRepository.saveAll(batteries);
    }

    public List<Battery> getBatteriesByPostcodeRange(String start, String end) {
        return batteryRepository.findByPostcodeBetween(start, end);
    }

}
