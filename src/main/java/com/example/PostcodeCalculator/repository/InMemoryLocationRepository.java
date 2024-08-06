package com.example.PostcodeCalculator.repository;

import com.example.PostcodeCalculator.model.Location;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static com.example.PostcodeCalculator.service.helper.writeToCSV;
import static com.example.PostcodeCalculator.service.helper.writeToLog;

@Repository
public class InMemoryLocationRepository implements LocationRepository {

    private final Map<String, Location> locationList = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(InMemoryLocationRepository.class);

    @PostConstruct
    public void init(){
        loadCsv();
    }

    private void loadCsv() {
        String line = null;
        try {
            FileReader fr = new FileReader("ukpostcodes.csv");
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); //to skip header
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                if(csv.length < 4){
                    writeToLog(String.format("coordinate not found: %s", line));
                }
                else {
                    locationList.put(csv[1], new Location(csv[1], toDoubleOrNull(csv[2]), toDoubleOrNull(csv[3])));
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            //logger.info(e.getLocalizedMessage());
            writeToLog(e.getLocalizedMessage());
        }
    }

    private double toDoubleOrNull(String stringValue){
        if (stringValue.isBlank()){
            return 0.0;
        }
        else{
            return Double.parseDouble(stringValue);
        }
    }

    public Location findCoordinateByPostCode(String postcode){
        return locationList.get(postcode);
    }

    public void updateCoordinateByPostCode(String postcode, String latitude, String longitude) {
        locationList.put(postcode, new Location(postcode, toDoubleOrNull(latitude), toDoubleOrNull(longitude)));
        writeToCSV(locationList);
    }

}
