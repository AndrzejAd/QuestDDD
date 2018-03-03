package com.quest.activities.application.internal;

import com.ddd.common.annotations.DomainService;

import java.util.Optional;

@DomainService
public interface CountryService {
    Optional<String> getCountryNameFromLatitudeAndLongitude(double latitude, double longitude);
}
