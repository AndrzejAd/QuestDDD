package com.quest.activities.domain.location;

import lombok.Value;

@Value
public class Location {
    private final static double radius = 20; // in kilometers
    private double latitude;
    private double longitude;
    private String countryName;
}
