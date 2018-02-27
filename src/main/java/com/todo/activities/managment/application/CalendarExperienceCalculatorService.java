package com.todo.activities.managment.application;

import com.ddd.common.annotations.DomainService;
import com.todo.activities.managment.domain.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@DomainService
public class CalendarExperienceCalculatorService implements ExperienceCalcService{

    @Override
    public long calculateExperienceGain(Activity activity, ActivitiesList activitiesList, User user) {
        ActivityType activityType = activity.getActivityType();
        long baseMultiplier = 1;
        if ( checkIfItsOnlyActivityOfThatTypeInList(user, activityType.getId()) ){ baseMultiplier += 2; }
        baseMultiplier += calculateBonusMultiplierForActiveActivities(activitiesList);
        baseMultiplier += dayOfTheWeekBonus(activity);
        return activityType.getBaseAward() * baseMultiplier;
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

    /**
     * Monday = bonus: + 2
     * Thursday, Wednesday = bonus: + 1
     * Thursday, Friday = bonus: + 0
     * Saturday, Sunday = bonus: + 1
     * @param activity
     * @return
     */
    protected double dayOfTheWeekBonus(Activity activity){
        double bonus = 0;
        DayOfWeek dayOfWeek = activity.getFinishDateTime().map(LocalDateTime::getDayOfWeek).orElse(DayOfWeek.FRIDAY);
        switch (dayOfWeek){
            case MONDAY:
                bonus += 2;
                break;

            case THURSDAY:
            case FRIDAY:
                bonus += 0;
                break;

            default:
                bonus += 1;
                break;
        }
        return bonus;
    }

}

