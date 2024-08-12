package com.example.PostcodeCalculator.service;

import com.example.PostcodeCalculator.model.Location;

import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class helper {
    private final static DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final static String separator = ",";

    public static void writeToLog(String errorMessage){
        try {
            FileWriter obj = new FileWriter("E:\\Programming\\JAVA\\PostcodeCalculator\\logs\\logs.csv", true);
            obj.write(java.time.LocalDateTime.now().format(formatter));
            obj.write(separator);
            obj.write(errorMessage);
            obj.write("\n");
            obj.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void writeToCSV(Map<String, Location> lineList ){
        try{
            FileWriter obj = new FileWriter("ukpostcodes.csv");
            int i = 0;
            for(Map.Entry<String, Location> line : lineList.entrySet()){
                i += 1;
                obj.write(String.valueOf(i));
                obj.write(separator);
                obj.write(line.getKey());
                obj.write(separator);
                obj.write(String.valueOf(line.getValue().getLatitude()));
                obj.write(separator);
                obj.write(String.valueOf(line.getValue().getLongitude()));
                obj.write(separator);
                obj.write("\n");
            }
            obj.close();
        } catch(Exception e) {
            writeToLog(e.getLocalizedMessage());
        }
    }
}
