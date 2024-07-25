package com.example.PostcodeCalculator.controller;

import com.example.PostcodeCalculator.entity.Location;
import com.example.PostcodeCalculator.entity.DistanceResult;
import com.example.PostcodeCalculator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class DistanceController {

    @Autowired
    private DistanceService distanceService;

    @GetMapping("/distance")
    public ResponseEntity<DistanceResult> getDistance(@RequestParam String postCode1, @RequestParam String postCode2) throws IOException {
        Location location1 = distanceService.getCoordinateByPostcode(postCode1);
        Location location2 = distanceService.getCoordinateByPostcode(postCode2);
        double distance = distanceService.calculateDistance(location1.getLatitude(), location1.getLongitude(),
                                                            location2.getLatitude(), location2.getLongitude());
        DistanceResult result = new DistanceResult(location1, location2, distance, "km");
        return ResponseEntity.ok(result);
    }
}
