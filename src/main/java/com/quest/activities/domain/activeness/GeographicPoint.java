package com.quest.activities.domain.activeness;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class GeographicPoint{
    private double longitude;
    private double latitude;

    private GeographicPoint(String longitude, String latitude) {
        this.longitude = convertLongitude(longitude);
        this.latitude = convertLatitude(latitude);
    }

    public static GeographicPoint of(String longitude, String latitude) {
        return new GeographicPoint(longitude, latitude);
    }

    double convertLongitude(String longitude){
        String trimed = longitude.trim()
                .replaceAll("°", " ")
                .replaceAll("'", " ")
                .replaceAll("N", " ")
                .trim();
        String numbers[] = trimed.split(" ");
        return Double.parseDouble(numbers[0])+ Double.parseDouble(numbers[1])/60 ;
    }

    double convertLatitude(String latitude){
        String trimed = latitude.trim()
                .replaceAll("°", " ")
                .replaceAll("'", " ")
                .replaceAll("E", " ")
                .trim();
        String numbers[] = trimed.split(" ");
        return Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1])/60 ;
    }

}