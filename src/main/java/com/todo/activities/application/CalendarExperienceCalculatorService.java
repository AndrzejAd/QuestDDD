package com.todo.activities.application;

import com.ddd.common.annotations.DomainService;
import com.todo.activities.domain.*;

@DomainService
public class CalendarExperienceCalculatorService implements ExperienceCalcService{
    private final double maxBonusForFirstActivities = 6;

    @Override
    public long calculateExperienceGain(Activity activity, ActivitiesList activitiesList, User user) {
        ActivityType activityType = activity.getActivityType();
        long baseMultiplier = 1;
        long baseAward = activity.getAward();
        if ( checkIfItsOnlyActivityOfThatTypeInList(user, activityType.getId()) ){
            baseMultiplier += 2;
        }
        baseMultiplier += calculateBonusMultiplierForActiveActivities(activitiesList);
        return baseAward * baseMultiplier;
    }

    protected boolean checkIfItsOnlyActivityOfThatTypeInList(User user, long activityTypeId){
         return user.getActivitiesList()
                 .stream()
                 .flatMap( activitiesList -> activitiesList.getActivities().stream())
                 .anyMatch(activity -> activity.getId() == activityTypeId );
    }

    protected double calculateBonusMultiplierForActiveActivities(ActivitiesList activitiesList){
        double bonus = 1;
        long numberOfFinishedActivities = activitiesList.getActivities()
                .stream()
                .filter( activity -> activity.getProgress() == Progress.DONE)
                .count();
        switch ( (int) numberOfFinishedActivities ){
            case 0:
                bonus = 6;
                break;
            case 1:
                bonus = 4;
                break;
            case 2:
                bonus = 2;
                break;
        }
        return bonus;
    }

}

