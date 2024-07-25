package com.example.PostcodeCalculator.service;

import com.example.PostcodeCalculator.entity.Location;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DistanceService {

    private final static double EARTH_RADIUS = 6371; // radius in kilometers

    public Location getCoordinateByPostcode(String postcode)
    throws IOException
    {
        FileReader fr = new FileReader("ukpostcodes.csv");
        BufferedReader br = new BufferedReader(fr);
        String line;
        Location locationData;
        while ((line = br.readLine()) != null) {
            // split on comma(',')
            String[] csv = line.split(",");

            if (Objects.equals(csv[1], postcode)) {
                return new Location(postcode, Double.parseDouble(csv[2]), Double.parseDouble(csv[3]));
            }
        }
        return new Location(postcode,0,0);
    }

    public double calculateDistance(double latitude, double longitude, double latitude2, double
            longitude2) {
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
