package com.example.PostcodeCalculator;

import com.example.PostcodeCalculator.controller.DistanceController;
import com.example.PostcodeCalculator.model.Location;
import com.example.PostcodeCalculator.service.DistanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DistanceController.class)
public class DistanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DistanceService distanceService;

    @BeforeEach
    public void setup(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetDistance() throws Exception {
        // Arrange
        String postCode1 = "AB10 1XG";
        String postCode2 = "AB11 5QN";
        Location location1 = new Location(postCode1, 57.144165, -2.114848);
        Location location2 = new Location(postCode2, 57.142701, -2.093295);
        double distance = 2.402099236950838; // Mocked distance value

        // Mock the service layer
        Mockito.when(distanceService.getCoordinate(postCode1)).thenReturn(location1);
        Mockito.when(distanceService.getCoordinate(postCode2)).thenReturn(location2);
        Mockito.when(distanceService.calculateDistance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude())).thenReturn(distance);

        // Act & Assert
        mockMvc.perform(get("/api/v1/distance")
                        .param("postCode1", postCode1)
                        .param("postCode2", postCode2))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.location1.postcode").value(postCode1))
                .andExpect(jsonPath("$.location1.latitude").value(location1.getLatitude()))
                .andExpect(jsonPath("$.location1.longitude").value(location1.getLongitude()))
                .andExpect(jsonPath("$.location2.postcode").value(postCode2))
                .andExpect(jsonPath("$.location2.latitude").value(location2.getLatitude()))
                .andExpect(jsonPath("$.location2.longitude").value(location2.getLongitude()))
                .andExpect(jsonPath("$.distance").value(distance))
                .andExpect(jsonPath("$.unit").value("km"))
                .andDo(MockMvcResultHandlers.print());
    }
}
