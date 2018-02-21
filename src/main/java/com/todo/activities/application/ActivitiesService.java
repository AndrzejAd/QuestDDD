package com.todo.activities.application;

import com.ddd.common.annotations.ApplicationService;
import com.ddd.common.validation.ContractBroken;
import com.todo.activities.application.commands.AddActivityCommand;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

@ApplicationService
public class ActivitiesService {
    private final ActivitiesListFactory activitiesListFactory;
    private final ActivitiesListRepository activitiesListRepository;
    private final UserRepository userRepository;
    private final ActivityFactory activityFactory;
    private final ActivityTypeRepository activityTypeRepository;

    @Autowired
    public ActivitiesService(ActivitiesListFactory activitiesListFactory, ActivitiesListRepository activitiesListRepository,
                             UserRepository userRepository, ActivityFactory activityFactory, ActivityTypeRepository activityTypeRepository) {
        this.activitiesListFactory = activitiesListFactory;
        this.activitiesListRepository = activitiesListRepository;
        this.userRepository = userRepository;
        this.activityFactory = activityFactory;
        this.activityTypeRepository = activityTypeRepository;
    }

    public void addNewActivitiesListToUser(CreateNewActivitiesListCommand createNewActivitiesListCommand) {
        User owningUser = userRepository
                .find(createNewActivitiesListCommand.getUserId())
                .orElseThrow(UserNotFound::new);
        ActivitiesList activitiesList = activitiesListFactory.createActivities(owningUser);
        owningUser.addActivities(activitiesList);
        userRepository.save(owningUser);
    }

    public void addActivityToActivityList(AddActivityCommand addActivityCommand){
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
        activitiesListRepository.save(owningActivitiesList);
    }


    public class UserNotFound extends ContractBroken { }

    public class ActivitiesListNotFound extends ContractBroken { }

}
