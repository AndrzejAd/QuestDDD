package com.quest.activities.application.service;

import com.quest.activities.application.commands.*;
import com.quest.activities.domain.activity.ActivitiesList;
import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.location.NearbyQuesters;
import com.quest.activities.domain.user.User;

import java.util.Collection;

public interface ActivitiesService {
    ActivitiesList addNewActivitiesListToUser(CreateNewActivitiesListCommand createNewActivitiesListCommand);
    Activity addActivityToActivityList(AddActivityCommand addActivityCommand);
    Activity startActivity(StartActivityCommand startActivityCommand);
    Activity finishActivity(FinishActivityCommand finishActivityCommand);
    NearbyQuesters getNearbyUsersBasedOnActivities(GetNearbyUsersCommand getNearbyUsersCommand);
}
