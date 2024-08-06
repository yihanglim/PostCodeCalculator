package com.example.PostcodeCalculator.service;

import com.example.PostcodeCalculator.model.Location;
import org.springframework.stereotype.Service;


@Service
public interface DistanceService {
    Location getCoordinate(String postCode);
    void updateCoordinate(String postCode, String latitude, String longitude);
    double calculateDistance(double latitude, double longitude, double latitude2, double longitude2);
}
