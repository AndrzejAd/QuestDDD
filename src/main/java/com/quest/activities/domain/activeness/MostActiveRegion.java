package com.quest.activities.domain.activeness;

import lombok.Value;

import java.util.Map;

@Value
public class MostActiveRegion {
    private Map.Entry<String, GeographicPoint> mostActiveRegion;
    private double activityPoints;
}



