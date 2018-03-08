package com.quest.activities.application.queries;

import com.ddd.common.annotations.Query;
import lombok.Value;

@Query
@Value
public class GetNearbyUsersQuery {
    private long userId;
}
