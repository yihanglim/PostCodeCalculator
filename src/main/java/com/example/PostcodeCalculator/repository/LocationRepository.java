package com.example.PostcodeCalculator.repository;

import com.example.PostcodeCalculator.model.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository {
    Location findCoordinateByPostCode(String postCode);
    void updateCoordinateByPostCode(String postcode, String latitude, String longitude);
}
