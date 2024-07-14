package com.bracit.virtualpowerplant.service;

import com.bracit.virtualpowerplant.model.Battery;

import java.util.List;

public interface BatteryService {

    Battery saveBattery(Battery battery);

    List<Battery> saveAllBatteries(List<Battery> batteries);

    List<Battery> getBatteriesByPostcodeRange(String start, String end);

}
