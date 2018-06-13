package com.quest.activities.domain.enticer;

import com.ddd.common.annotations.DomainService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@DomainService
public class SmogEnticer extends Enticer {
    private final Logger logger = LogManager.getLogger(SmogEnticer.class);
    private final String stationsRequestPrefix = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
    private final String phRequestPrefix = "http://api.gios.gov.pl/pjp-api/rest/data/getData/"; // {sensorId}
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final TaskExecutor taskExecutor;


    public SmogEnticer(List<EnticerObserver> enticmentObservers, TaskExecutor taskExecutor) {
        super(enticmentObservers);
        this.taskExecutor = taskExecutor;
    }

    @PostConstruct
    public void execute() {
        taskExecutor.execute(() ->
            enticeActivityMultiplier()
        );
    }

    @Override
    public double enticeActivityMultiplier() {
        logger.info("Calculating air level...");
        double maxPH = getIdsOfStations()
                .stream()
                .map(this::getPHForStation)
                .max(Comparator.comparingDouble(Double::doubleValue))
                .orElse(0.0);
        if ( maxPH < 10 ) return 2;
        if ( maxPH >= 10 && maxPH <= 50 ) return 1;
        if ( maxPH > 50 ) return 0.25;
        return 1;
    }

    List<Integer> getIdsOfStations(){
        List<Integer> arrayOfIds = new ArrayList<>();
        Request request = new Request.Builder()
                .url(stationsRequestPrefix)
                .build();
        try {
            String jsonData = okHttpClient.newCall(request).execute().body().string();
            JSONArray jarray = new JSONArray(jsonData);
            for (int i = 0; i < jarray.length(); i++) {
                arrayOfIds.add(jarray.getJSONObject(i).getInt("id"));
            }
            return arrayOfIds;
        } catch (IOException | JSONException e) {
            logger.debug("Couldn't get response from Rest Air API" + e.getMessage());
            return arrayOfIds;
        }
    }

    double getPHForStation(int id){

        Request request = new Request.Builder()
                .url(phRequestPrefix + id)
                .build();
        try {
            String jsonData = okHttpClient.newCall(request).execute().body().string();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jarray = jsonObject.getJSONArray("values");
            if ( jarray.length() > 0 ){
                logger.info("Got response for: " + id);
                return jarray.getJSONObject(0).getDouble("value");
            } else{
                return 0.0;
            }
        } catch (IOException | JSONException e) {
            logger.debug("Couldn't get response from Rest Air API for id: " + id + "\nMessage: " + e.getMessage());
            return 0.0;
        }
    }

}
