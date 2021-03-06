package com.quest.activities.ui;

import com.quest.activities.application.commands.*;
import com.quest.activities.application.service.IActivitiesCommandService;
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
public class ActivitiesCommandRestController {
    private final IActivitiesCommandService IActivitiesCommandService;

    @RequestMapping(path = "/addNewActivitiesList", method = RequestMethod.POST)
    public ResponseEntity<String> addNewActivitiesList(@RequestParam("userId") final long userId){
        IActivitiesCommandService.addNewActivitiesListToUser(
                new CreateNewActivitiesListCommand(userId)
        );
        return new ResponseEntity<>("List created!", HttpStatus.CREATED);
    }

    @RequestMapping(path = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<String> addActivity(@RequestParam("userId") final long userId,
                                              @RequestParam("activityTypeId") final long activityTypeId,
                                              @RequestParam("longitude") final double longitude,
                                              @RequestParam("longitude") final double latitude){
        IActivitiesCommandService.addActivityToActivityList(
                new AddActivityCommand(userId, activityTypeId, longitude, latitude)
        );
        return new ResponseEntity<>("Activity added to user!", HttpStatus.CREATED);
    }

    @RequestMapping(path = "/startActivity", method = RequestMethod.POST)
    public ResponseEntity<String> startActivity(@RequestParam("activityId") final long activityId){
        IActivitiesCommandService.startActivity(
                new StartActivityCommand(activityId)
        );
        return new ResponseEntity<>("Started activity!", HttpStatus.OK);
    }

    @RequestMapping(path = "/finishActivity", method = RequestMethod.POST)
    public ResponseEntity<String> finishActivity(@RequestParam("activityId") final long activityId){
        IActivitiesCommandService.finishActivity(
                new FinishActivityCommand(activityId)
        );
        return new ResponseEntity<>("Finished activity!", HttpStatus.OK);
    }


}
