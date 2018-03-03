package com.quest.activities.domain.location;

import com.ddd.common.annotations.DomainService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@DomainService
public class RestCountryService implements CountryService {
    private final Logger logger = LogManager.getLogger(CountryService.class);
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final String requestPrefix = " http://api.geonames.org/countryCode?";
    private final String username = "questDDD";

    @Override
    /**
     * Example request =
     * http://api.geonames.org/countryCode?lat=47.03&lng=10.2&username=demo
     */
    public Optional<String> getCountryNameFromLatitudeAndLongitude(double latitude, double longitude) {
        double twoPrecisionLatitude = Math.round(latitude * 100.0) / 100.0;
        double twoPrecisionLongitude = Math.round(latitude * 100.0) / 100.0;
        Locale locale = new Locale("", getResponseFromApi(twoPrecisionLatitude, twoPrecisionLongitude));
        return Optional.of(locale.getDisplayCountry());
    }

    public String getResponseFromApi(double latitude, double longitude){
        Request request = new Request.Builder()
                .url(requestPrefix + "lat=" + latitude + "&lng=" + longitude + "&username=" + username )
                .build();
        try {
            return okHttpClient.newCall(request).execute().body().string().trim();
        } catch (IOException e) {
            logger.warn("Couldn't get response from Rest Countries API" + e.getMessage());
            return "";
        }
    }

}
