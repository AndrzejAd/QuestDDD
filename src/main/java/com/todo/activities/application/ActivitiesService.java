package com.todo.activities.application;

import com.ddd.common.annotations.ApplicationService;
import com.ddd.common.validation.ContractBroken;
import com.todo.activities.application.commands.AddActivityCommand;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.application.commands.FinishActivityCommand;
import com.todo.activities.application.commands.StartActivityCommand;
import com.todo.activities.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

@ApplicationService
public class ActivitiesService {
    private final ActivitiesListFactory activitiesListFactory;
    private final ActivitiesListRepository activitiesListRepository;
    private final UserRepository userRepository;
    private final ActivityFactory activityFactory;
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivitiesService(ActivitiesListFactory activitiesListFactory, ActivitiesListRepository activitiesListRepository,
                             UserRepository userRepository, ActivityFactory activityFactory,
                             ActivityTypeRepository activityTypeRepository, ActivityRepository activityRepository) {
        this.activitiesListFactory = activitiesListFactory;
        this.activitiesListRepository = activitiesListRepository;
        this.userRepository = userRepository;
        this.activityFactory = activityFactory;
        this.activityTypeRepository = activityTypeRepository;
        this.activityRepository = activityRepository;
    }

    public ActivitiesList addNewActivitiesListToUser(CreateNewActivitiesListCommand createNewActivitiesListCommand) {
        User owningUser = userRepository
                .find(createNewActivitiesListCommand.getUserId())
                .orElseThrow(UserNotFound::new);
        ActivitiesList activitiesList = activitiesListFactory.createActivities(owningUser);
        owningUser.addActivities(activitiesList);
        userRepository.save(owningUser);
        return activitiesList;
    }

    public Activity addActivityToActivityList(AddActivityCommand addActivityCommand){
        ActivitiesList owningActivitiesList = activitiesListRepository
                .findById(addActivityCommand.getActivitiesListId())
                .orElseThrow(ActivitiesListNotFound::new);
        Activity newActivity = activityFactory.createActivities(
                addActivityCommand.getDescription(),
                addActivityCommand.getExpectedDuration(),
                addActivityCommand.getBaseAward(),
                owningActivitiesList
        );
        owningActivitiesList.addActivity(newActivity);
        activityTypeRepository.save(newActivity.getActivityType());
        activityRepository.save(newActivity);
        activitiesListRepository.save(owningActivitiesList);
        return newActivity;
    }

    public Activity startActivity(StartActivityCommand startActivityCommand){
        Activity activity = activityRepository
                .findById(startActivityCommand.getActivityId())
                .orElseThrow(ActivityNotFound::new);
        activity.startActivity();
        return activityRepository.save(activity);
    }

    public Activity finishActivity(FinishActivityCommand finishActivityCommand){
        Activity activity = activityRepository
                .findById(finishActivityCommand.getActivityId())
                .orElseThrow(ActivityNotFound::new);
        activity.finishActivity();
        return activityRepository.save(activity);
    }

    public class UserNotFound extends ContractBroken { }

    public class ActivitiesListNotFound extends ContractBroken { }

    public class ActivityNotFound extends ContractBroken { }

}
