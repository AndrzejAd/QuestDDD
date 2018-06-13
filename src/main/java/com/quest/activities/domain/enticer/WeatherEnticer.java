package com.quest.activities.domain.enticer;

import com.ddd.common.annotations.DomainService;

import java.util.List;

@DomainService
public class WeatherEnticer extends Enticer {

    public WeatherEnticer(List<EnticerObserver> enticmentObservers) {
        super(enticmentObservers);
    }

    @Override
    public double enticeActivityMultiplier() {
        return 1;
    }
}
