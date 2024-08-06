package com.example.PostcodeCalculator;

import com.example.PostcodeCalculator.repository.LocationRepository;
import com.example.PostcodeCalculator.service.DistanceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DistanceServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private DistanceServiceImpl distanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	@Test
	public void testCalculateDistance() {
        //Arrange
        String postCode1 = "AB10 1XG"; //for reference only
        String postCode2 = "AB11 5QN"; //for reference only
        double latitude1 = -2.114848;
        double latitude2 = -2.093295;
        double longitude1 = 57.144165;
        double longitude2 = 57.142701;
        double expectedDistance = 2.402099236950838;

        //Act
        double distance = distanceService.calculateDistance(latitude1, longitude1, latitude2, longitude2);

        //Assert
        assertEquals(expectedDistance, distance, 0.001);
    }
}
