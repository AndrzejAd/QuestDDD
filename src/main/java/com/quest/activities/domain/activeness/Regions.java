package com.quest.activities.domain.activeness;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


enum Regions {

    POLISH_REGIONS{
        @Getter
        public final Map<String, GeographicPoint> polishRegions = Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("Zachodniopomorskie", GeographicPoint.of("53°26'N", "14°32'E")),
                new AbstractMap.SimpleEntry<>("Pomorskie", GeographicPoint.of("54°20'N ", "18°38'E")),
                new AbstractMap.SimpleEntry<>("Warmińsko-Mazurskie", GeographicPoint.of("53°47'N", "20°30'E")),
                new AbstractMap.SimpleEntry<>("Podlaskie", GeographicPoint.of("53°08'N", "23°08'E")),
                new AbstractMap.SimpleEntry<>("Mazowieckie", GeographicPoint.of("52°13'N", "21°00'E")),
                new AbstractMap.SimpleEntry<>("Wielkopolskie", GeographicPoint.of("52°24'N", "16°56'E")),
                new AbstractMap.SimpleEntry<>("Lubuskie", GeographicPoint.of("52°43'N", "15°14'E")),
                new AbstractMap.SimpleEntry<>("Lubuskie1", GeographicPoint.of("51°56'N", "15°30'E")),
                new AbstractMap.SimpleEntry<>("Dolnośląskie", GeographicPoint.of("51°06'N", "17°01'E")),
                new AbstractMap.SimpleEntry<>("Opolskie", GeographicPoint.of("50°39'N", "17°55'E")),
                new AbstractMap.SimpleEntry<>("Łódzkie ", GeographicPoint.of("51°46'N", "19°27'E")),
                new AbstractMap.SimpleEntry<>("Śląskie ", GeographicPoint.of("50°15'N", "19°01'E ")),
                new AbstractMap.SimpleEntry<>("Świętokrzyskie", GeographicPoint.of("50°53'N", "20°37'E")),
                new AbstractMap.SimpleEntry<>("Małopolskie ", GeographicPoint.of("50°03'N", "19°56'E")),
                new AbstractMap.SimpleEntry<>("Lubelskie ", GeographicPoint.of("51°14'N", "22°34'E")),
                new AbstractMap.SimpleEntry<>("Podkarpackie", GeographicPoint.of("50°02'N", "22°00'E")))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));

        @Override
        public Map<String, GeographicPoint> getRegions() {
            return polishRegions;
        }
    };

    abstract Map<String, GeographicPoint> getRegions();

}
