package com.quest.activities.application.internal;

import com.quest.activities.domain.location.RestCountryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestCountryServiceTest {

    static RestCountryService restCountryService;

    @BeforeAll
    public static void setRestCountryService(){
        restCountryService = new RestCountryService();
    }

    @Test
    void getCountryNameFromLatitudeAndLongitude() {
        // given
        // when
        // then
        assertEquals("Kazachstan", restCountryService
                .getCountryNameFromLatitudeAndLongitude(50.5, 45.5)
                .orElse("Nothing!"));
    }
}