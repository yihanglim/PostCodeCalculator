package com.example.PostcodeCalculator.model;

public class Location {
    private String postcode;
    private double longitude;
    private double latitude;

    public Location(String postcode, double longitude, double latitude){
        this.postcode = postcode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getPostcode() {
        return postcode;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

//    public void setPostcode(String postcode) {
//        this.postcode = postcode;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
}
