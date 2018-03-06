package com.quest.activities.domain.location.dto;

import lombok.Value;

@Value
public class Location {
    private double latitude;
    private double longitude;
    private String countryName;
}
