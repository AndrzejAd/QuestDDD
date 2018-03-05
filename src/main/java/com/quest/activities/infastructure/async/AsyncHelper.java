package com.quest.activities.infastructure.async;

import com.quest.activities.application.service.exceptions.ActivitiesListNotFound;
import com.quest.activities.application.service.exceptions.ActivityTypeNotFound;
import com.quest.activities.domain.activity.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncHelper {

    @Async
    public CompletableFuture<ActivitiesList> getActivitiesListFuture(long id, ActivitiesListRepository activitiesListRepository){
        return CompletableFuture.supplyAsync( () -> activitiesListRepository
                .findById(id)
                .<ActivitiesListNotFound>orElseThrow(ActivitiesListNotFound::new));
    }


    @Async
    public CompletableFuture<ActivityType> getActivityTypeFuture(long id, ActivityTypeRepository activityTypeRepository){
        return CompletableFuture.supplyAsync( () -> activityTypeRepository
                .findById(id)
                .<ActivityTypeNotFound>orElseThrow(ActivityTypeNotFound::new));
    }


    @Async
    public CompletableFuture<Activity> getActivityFuture(CompletableFuture<ActivityType> owningActivitiesListFuture,
                                                         CompletableFuture<ActivitiesList>  typeOfActivityFuture,
                                                         double longitude, double latitude){
        return owningActivitiesListFuture
                .thenCombine( typeOfActivityFuture,(activityList, typeOfActivity) ->
                        new Activity(activityList, typeOfActivity, longitude, latitude));
    }

}
