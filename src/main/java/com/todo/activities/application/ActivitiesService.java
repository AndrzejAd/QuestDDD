package com.todo.activities.application;

import com.ddd.common.annotations.ApplicationService;
import com.ddd.common.validation.ContractBroken;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

@ApplicationService
public class ActivitiesService {
    private final ActivitiesFactory activitiesFactory;
    private final ActivitiesRepository activitiesRepository;
    private final UserRepository userRepository;

    @Autowired
    public ActivitiesService(ActivitiesFactory activitiesFactory, ActivitiesRepository activitiesRepository, UserRepository userRepository) {
        this.activitiesFactory = activitiesFactory;
        this.activitiesRepository = activitiesRepository;
        this.userRepository = userRepository;
    }

    public void addActivitiesListToUser(CreateNewActivitiesListCommand createNewActivitiesListCommand) {
        User user = userRepository
                .find(createNewActivitiesListCommand.getUserId())
                .orElseThrow(UserNotFound::new);
        Activities activities = activitiesFactory.createActivities(user);
        user.addActivities(activities);
        userRepository.save(user);
    }

    public class UserNotFound extends ContractBroken {
    }

}
