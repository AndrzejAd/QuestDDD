package com.todo.activities.application;

import com.ddd.common.annotations.ApplicationService;
import com.ddd.common.events.ActivityIsFinished;
import com.ddd.common.validation.ContractBroken;
import com.todo.activities.application.commands.AddActivityCommand;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.application.commands.FinishActivityCommand;
import com.todo.activities.application.commands.StartActivityCommand;
import com.todo.activities.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@ApplicationService
@Transactional
public class ActivitiesService {
    private final UserRepository userRepository;
    private final ActivitiesListRepository activitiesListRepository;
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityRepository activityRepository;
    private final ActivityFactory activityFactory;
    private final ActivitiesListFactory activitiesListFactory;
    private final ExperienceCalcService experienceCalcService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ActivitiesService(ActivitiesListFactory activitiesListFactory, ActivitiesListRepository activitiesListRepository,
                             UserRepository userRepository, ActivityFactory activityFactory,
                             ActivityTypeRepository activityTypeRepository, ActivityRepository activityRepository,
                             ExperienceCalcService experienceCalcService, ApplicationEventPublisher applicationEventPublisher) {
        this.activitiesListFactory = activitiesListFactory;
        this.activitiesListRepository = activitiesListRepository;
        this.userRepository = userRepository;
        this.activityFactory = activityFactory;
        this.activityTypeRepository = activityTypeRepository;
        this.activityRepository = activityRepository;
        this.experienceCalcService = experienceCalcService;
        this.applicationEventPublisher = applicationEventPublisher;
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
        ActivityType typeOfActivity = activityTypeRepository
                .findById(addActivityCommand.getActivityTypeId())
                .orElseThrow(ActivityTypeNotFound::new);
        Activity newActivity = activityFactory.createActivities(typeOfActivity, owningActivitiesList,
                addActivityCommand.getLongitude(),
                addActivityCommand.getLatitude());
        owningActivitiesList.addActivity(newActivity);
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
        User owningUser = userRepository.find(activity.getActivitiesList().getUser().getId()).get();
        activity.finishActivity();
        activity.setAward(experienceCalcService.calculateExperienceGain(activity, activity.getActivitiesList(), owningUser ));
        notifyUserAboutFinishingActivity(activity, owningUser);
        return activityRepository.save(activity);
    }

    protected void notifyUserAboutFinishingActivity(Activity activity, User owningUser){
        applicationEventPublisher.publishEvent(
                new ActivityIsFinished(this,
                        activity.getId(),
                        owningUser.getEmailAddress(),
                        owningUser.getUsername(),
                        LocalDateTime.now(),
                        activity.getAward()));
    }

    public class UserNotFound extends ContractBroken { }

    public class ActivitiesListNotFound extends ContractBroken { }

    public class ActivityNotFound extends ContractBroken { }

    public class ActivityTypeNotFound extends ContractBroken { }

}
