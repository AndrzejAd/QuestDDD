package com.quest.activities.domain.activity.dto;

import com.quest.activities.domain.activity.Activity;
import com.quest.activities.domain.user.User;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value @Builder
public class ActivitiesListDto {
    private User user;
    private List<Activity> activities;
}
