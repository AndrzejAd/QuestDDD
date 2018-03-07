package com.quest.activities.ui;

import com.quest.activities.application.commands.*;
import com.quest.activities.application.service.IActivitiesQueryService;
import com.quest.activities.domain.location.dto.NearbyQuestersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActivitiesQueryRestController {
    private final IActivitiesQueryService iActivitiesQueryService;

    @RequestMapping(path = "/getNearbyUsers", method = RequestMethod.GET)
    public ResponseEntity<NearbyQuestersDto> getNearbyUsers(@RequestParam("userId") final long userId){
        return new ResponseEntity<>(iActivitiesQueryService.getNearbyUsersBasedOnActivities(new GetNearbyUsersCommand(userId)), HttpStatus.CREATED);
    }

}
