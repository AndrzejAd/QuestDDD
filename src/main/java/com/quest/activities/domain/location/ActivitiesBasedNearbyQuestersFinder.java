package com.quest.activities.domain.location;

import com.ddd.common.annotations.DomainService;
import com.quest.activities.domain.activity.ActivityRepository;
import com.quest.activities.domain.location.dto.Location;
import com.quest.activities.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;


@DomainService
@RequiredArgsConstructor
public class ActivitiesBasedNearbyQuestersFinder implements NearbyQuestersFinder {
    private final ActivityRepository activityRepository;
    private final CountryService countryService;

    /**
     * Should think about keeping reference to a user in Activity class - the complexity of this algorithm
     * may be pretty bad
     *
     * @param user
     * @param radius in km
     * @return
     */
    @Override
    public NearbyQuesters getNearbyUsers(User user, double radius) {
        Collection<Location> locations = getUserLocations(user);
        Location representativeLocation = getRepresentativeLocation(locations);
        Pair<Double, Double> radiusOfArea = convertKmToLatitudeAndLongitude(radius);
        Collection<User> nearbyUsers = nearbyUsersByLocationAndRadius(representativeLocation, radiusOfArea);
        return new NearbyQuesters(representativeLocation, nearbyUsers );
    }

    protected Collection<Location> getUserLocations(User user) {
        return user
                .getActivitiesList()
                .stream()
                .flatMap(activityList -> activityList.getActivities().stream())
                .map(activity -> {
                    double latitude = activity.getLatitude();
                    double longitude = activity.getLongitude();
                    return new Location(
                            latitude,
                            longitude,
                            countryService.getCountryNameFromLatitudeAndLongitude(latitude, longitude).orElse("Undefined")
                    );
                })
                .collect(Collectors.toList());
    }

    protected Location getRepresentativeLocation(Collection<Location> locations) {
        double averageLatitude = getAverageValuesFromLocation(locations, Location::getLatitude);
        double averageLongitude = getAverageValuesFromLocation(locations, Location::getLongitude);
        return new Location(
                averageLatitude,
                averageLongitude,
                countryService.getCountryNameFromLatitudeAndLongitude(averageLatitude, averageLongitude).orElse("Undefined")
        );
    }

    /**
     * Ye, it will SUCK if user travels a lot. Lets hope he does not.
     *
     * @param locations
     * @param toDoubleFunction
     * @return
     */
    protected double getAverageValuesFromLocation(Collection<Location> locations, ToDoubleFunction<? super Location> toDoubleFunction) {
        return locations
                .stream()
                .mapToDouble(toDoubleFunction)
                .average()
                .orElse(0.0);
    }

    /**
     * 1 latitude = 110.575
     * 1 longitude = 111.320*cos(latitude (radians)) km
     *
     * @param radius
     * @return
     */
    protected Pair<Double, Double> convertKmToLatitudeAndLongitude(double radius) {
        double latitude = radius / 110.575;
        return Pair.of(latitude, 111.320 * Math.toRadians(latitude));
    }

    protected Collection<User> nearbyUsersByLocationAndRadius(Location representativeLocation, Pair<Double, Double> radiusOfArea) {
        return activityRepository.findByLatitudeGreaterThanAndLatitudeLessThanAndLongitudeGreaterThanAndLongitudeLessThan(
                representativeLocation.getLatitude() - radiusOfArea.getFirst(),
                representativeLocation.getLatitude() + radiusOfArea.getFirst(),
                representativeLocation.getLongitude() - radiusOfArea.getSecond(),
                representativeLocation.getLongitude() + radiusOfArea.getSecond()
            )
            .stream()
            .map(x -> x.getActivitiesList().getUser())
            .collect(Collectors.toCollection(ArrayList::new));
    }

}
