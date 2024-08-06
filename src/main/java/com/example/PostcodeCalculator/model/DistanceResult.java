package com.example.PostcodeCalculator.model;

public class DistanceResult {
    private Location location1;
    private Location location2;
    private double distance;
    private String unit;

    public DistanceResult(Location location1, Location location2, double distance, String unit) {
        this.location1 = location1;
        this.location2 = location2;
        this.distance = distance;
        this.unit = unit;
    }

    public double getDistance() {
        return distance;
    }

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    public String getUnit() {
        return unit;
    }
}
