package com.bracit.virtualpowerplant.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatteryStatistics {
    private List<String> batteryNames;
    private int totalWattCapacity;
    private double averageWattCapacity;

    public BatteryStatistics(List<String> batteryNames, int totalWattCapacity, double averageWattCapacity) {
        this.batteryNames = batteryNames;
        this.totalWattCapacity = totalWattCapacity;
        this.averageWattCapacity = averageWattCapacity;
    }
}
