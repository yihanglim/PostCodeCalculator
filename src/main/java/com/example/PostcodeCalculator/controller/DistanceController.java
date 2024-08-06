package com.example.PostcodeCalculator.controller;

import com.example.PostcodeCalculator.model.DistanceResult;
import com.example.PostcodeCalculator.model.Location;
import com.example.PostcodeCalculator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class DistanceController {

    @Autowired
    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService){
        this.distanceService = distanceService;
    }

    @GetMapping("/distance")
    public ResponseEntity<DistanceResult> getDistance(@RequestParam String postCode1, @RequestParam String postCode2) throws Exception {
        try{
            Location location1 = distanceService.getCoordinate(postCode1);
            Location location2 = distanceService.getCoordinate(postCode2);
            if (location1 == null || location2 == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            double distance = distanceService.calculateDistance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude());
            DistanceResult result = new DistanceResult(location1, location2, distance, "km");
            return ResponseEntity.ok(result);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/location/update")
    public ResponseEntity<Location> update(@RequestBody Location location) throws Exception {
        try{
            if (location.getPostcode() == null){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "postcode / latitude / longitude must not be empty");
            }
            distanceService.updateCoordinate(location.getPostcode(), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
            Location update = distanceService.getCoordinate(location.getPostcode());
            return ResponseEntity.ok(update);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
