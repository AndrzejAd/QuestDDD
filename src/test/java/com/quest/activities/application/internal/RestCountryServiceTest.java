package com.quest.activities.application.internal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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