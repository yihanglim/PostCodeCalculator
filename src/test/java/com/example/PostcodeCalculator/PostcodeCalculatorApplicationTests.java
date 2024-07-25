package com.example.PostcodeCalculator;

import com.example.PostcodeCalculator.entity.Location;
import com.example.PostcodeCalculator.service.DistanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PostcodeCalculatorApplicationTests {

    private DistanceService distanceService;

    @BeforeEach
    void setUp() {
        distanceService = new DistanceService();
    }
	@Test
	void testCalculateDistance() {
        Location location1 = new Location("AB10 1XG", 57.144165, -2.114848);
        Location location2 = new Location("AB11 5QN", 57.142701, -2.093295);
        double expectedDistance = 2.402099236950838;
        double distance = distanceService.calculateDistance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude());

        assertNotNull(distance);
        assertEquals(expectedDistance, distance, 0.001);
    }

    @Test
    void getCoordinateByPostcode() throws IOException {
        String postCode1 = "AB10 1XG";
        double expectedLatitude = -2.114848;
        double expectedLongitude = 57.144165;
        Location result = distanceService.getCoordinateByPostcode(postCode1);

        assertNotNull(result);
        assertEquals(expectedLatitude, result.getLatitude());
        assertEquals(expectedLongitude, result.getLongitude());
    }

}
