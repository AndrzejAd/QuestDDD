package com.todo.activities.application.service;

import com.todo.activities.application.commands.AddActivityCommand;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.application.commands.FinishActivityCommand;
import com.todo.activities.application.commands.StartActivityCommand;
import com.todo.activities.domain.activity.ActivitiesList;
import com.todo.activities.domain.activity.Activity;
import com.todo.activities.domain.user.User;

import java.util.Collection;

public interface ActivitiesService {
    ActivitiesList addNewActivitiesListToUser(CreateNewActivitiesListCommand createNewActivitiesListCommand);
    Activity addActivityToActivityList(AddActivityCommand addActivityCommand);
    Activity startActivity(StartActivityCommand startActivityCommand);
    Activity finishActivity(FinishActivityCommand finishActivityCommand);
    Collection<User> getNearbyUsersBasedOnActivities();
}
