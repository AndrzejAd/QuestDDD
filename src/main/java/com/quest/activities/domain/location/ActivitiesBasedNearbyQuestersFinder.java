package com.quest.activities.domain.location;

import com.ddd.common.annotations.DomainService;
import com.quest.activities.domain.activity.ActivityRepository;
import com.quest.activities.domain.location.NearbyQuesters;
import com.quest.activities.domain.location.NearbyQuestersFinder;
import com.quest.activities.domain.user.User;
import com.quest.activities.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@DomainService
@RequiredArgsConstructor
public class ActivitiesBasedNearbyQuestersFinder implements NearbyQuestersFinder {
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final CountryService countryService;


    /**
     * Cant really think of any optimised way to search through all activities so will just choose at random.
     * @param user
     * @return
     */
    @Override
    public NearbyQuesters getNearbyUsers(User user) {
        Collection<Location> locations = getUserLocations(user);

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

}
