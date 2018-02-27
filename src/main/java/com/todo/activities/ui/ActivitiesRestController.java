package com.todo.activities.ui;

import com.todo.activities.application.service.ActivitiesService;
import com.todo.activities.application.service.ActivitiesServiceImpl;
import com.todo.activities.application.commands.AddActivityCommand;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
import com.todo.activities.application.commands.FinishActivityCommand;
import com.todo.activities.application.commands.StartActivityCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitiesRestController {
    private final ActivitiesService activitiesService;

    @Autowired
    public ActivitiesRestController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @RequestMapping(path = "/addNewActivitiesList", method = RequestMethod.POST)
    public ResponseEntity<String> addNewActivitiesList(@RequestParam("userId") final long userId){
        activitiesService.addNewActivitiesListToUser(
                new CreateNewActivitiesListCommand(userId)
        );
        return new ResponseEntity<>("List created!", HttpStatus.CREATED);
    }

    @RequestMapping(path = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<String> addActivity(@RequestParam("userId") final long userId,
                                              @RequestParam("activityTypeId") final long activityTypeId,
                                              @RequestParam("longitude") final long longitude,
                                              @RequestParam("longitude") final long latitude){
        activitiesService.addActivityToActivityList(
                new AddActivityCommand(userId, activityTypeId, longitude, latitude)
        );
        return new ResponseEntity<>("Activity added to user!", HttpStatus.CREATED);
    }

    @RequestMapping(path = "/startActivity", method = RequestMethod.POST)
    public ResponseEntity<String> startActivity(@RequestParam("activityId") final long activityId){
        activitiesService.startActivity(
                new StartActivityCommand(activityId)
        );
        return new ResponseEntity<>("Started activity!", HttpStatus.OK);
    }

    @RequestMapping(path = "/finishActivity", method = RequestMethod.POST)
    public ResponseEntity<String> finishActivity(@RequestParam("activityId") final long activityId){
        activitiesService.finishActivity(
                new FinishActivityCommand(activityId)
        );
        return new ResponseEntity<>("Finished activity!", HttpStatus.OK);
    }

}
