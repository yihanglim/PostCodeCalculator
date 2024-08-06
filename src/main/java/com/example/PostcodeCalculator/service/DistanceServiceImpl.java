package com.example.PostcodeCalculator.service;

import com.example.PostcodeCalculator.model.Location;
import com.example.PostcodeCalculator.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {

    private final static double EARTH_RADIUS = 6371; // radius in kilometers

    private final LocationRepository locationRepository;

    public DistanceServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public Location getCoordinate(String postCode){
        return locationRepository.findCoordinateByPostCode(postCode);
    }
    public void updateCoordinate(String postCode, String latitude, String longitude){ locationRepository.updateCoordinateByPostCode(postCode, latitude, longitude); }

    public double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {

        // Using Haversine formula! See Wikipedia;
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (EARTH_RADIUS * c);
    }
    private double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }
    private double square(double x) {
        return x * x;
    }
}
