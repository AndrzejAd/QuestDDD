package com.quest.activities.domain.activeness;

import com.ddd.common.annotations.DomainService;
import com.ddd.common.domain.IGenericCrudDao;
import com.quest.activities.domain.activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.quest.activities.domain.activeness.Regions.POLISH_REGIONS;

@DomainService
public class BasicMostActiveRegionCalculator implements MostActiveRegionCalculatorService {
    private final IGenericCrudDao<Activity> activitiesGenericCrudDao;
    private Map<Map.Entry<String, GeographicPoint>, Double> activitiesScoreInRegions;

    @Autowired
    public BasicMostActiveRegionCalculator(IGenericCrudDao<Activity> activitiesGenericCrudDao) {
        this.activitiesGenericCrudDao = activitiesGenericCrudDao;
        this.activitiesScoreInRegions = new HashMap<>();
        initializeScoresMap();
    }

    @Override
    public MostActiveRegion calculateMostActiveRegion() {
        updateActivitiesScores();
        return activitiesScoreInRegions
                .entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(stringDoubleEntry -> new MostActiveRegion(stringDoubleEntry.getKey(), stringDoubleEntry.getValue()))
                .orElse(new MostActiveRegion(new AbstractMap.SimpleEntry<>("There are no regions!", new GeographicPoint(0, 0)), 0.0));
    }

    private void updateActivitiesScores() {
        activitiesGenericCrudDao
                .findAll()
                .forEach(activity -> {
                    Map.Entry<String, GeographicPoint> closestRegion = findClosestRegionForCoordinates(activity.getLongitude(), activity.getLatitude());
                    double currentValue = activitiesScoreInRegions.get(closestRegion);
                    activitiesScoreInRegions.put(closestRegion, currentValue + (double) activity.getAward());
                });
    }

    private Map.Entry<String, GeographicPoint> findClosestRegionForCoordinates(double longitude, double latitude) {
        return activitiesGenericCrudDao
                .findAll()
                .stream()
                .max((o1, o2) -> distance(o1.getLongitude(), o1.getLatitude(), longitude, latitude) <= distance(o2.getLongitude(), o2.getLatitude(), longitude, latitude) ? -1 : 1)
                .map(activity -> {
                    double activityLongitude = activity.getLongitude();
                    double activityLatitude = activity.getLatitude();
                    return new AbstractMap.SimpleEntry<>(findRegionNameByLongitudeAndLatitude(activityLongitude, activityLatitude), new GeographicPoint(activityLongitude, activityLatitude));
                })
                .orElse(new AbstractMap.SimpleEntry<>("There are no regions!", new GeographicPoint(0, 0)));

    }

    private double distance(double longitude, double latitude, double longitude1, double latitude1) {
        return Math.pow(longitude - longitude1, 2) + Math.pow(latitude - latitude1, 2);
    }

    private String findRegionNameByLongitudeAndLatitude(double longitude, double latitude) {
        return POLISH_REGIONS
                .getRegions()
                .entrySet()
                .stream()
                .filter(stringGeographicPointEntry -> stringGeographicPointEntry.getValue().getLongitude() == longitude && stringGeographicPointEntry.getValue().getLatitude() == latitude)
                .limit(0)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("UNDEFINED");
    }

    private void initializeScoresMap() {
        POLISH_REGIONS.getRegions().forEach((region, coord) -> activitiesScoreInRegions.put(new AbstractMap.SimpleEntry<>(region, new GeographicPoint(coord.getLongitude(), coord.getLatitude())), 0.0));
    }

}
