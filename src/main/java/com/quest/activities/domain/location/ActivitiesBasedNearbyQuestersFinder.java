package com.quest.activities.domain.location;

import com.ddd.common.annotations.DomainService;
import com.quest.activities.domain.activity.ActivityRepository;
import com.quest.activities.domain.user.User;
import com.quest.activities.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;


@DomainService
@RequiredArgsConstructor
public class ActivitiesBasedNearbyQuestersFinder implements NearbyQuestersFinder {
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final CountryService countryService;


    /**
     * @param user
     * @return
     */
    @Override
    public NearbyQuesters getNearbyUsers(User user, double radius) {
        Collection<Location> locations = getUserLocations(user);
        Location representativeLocation = getRepresentativeLocation(locations);

        return null;
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

}
