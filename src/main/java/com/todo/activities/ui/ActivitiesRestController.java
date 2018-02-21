package com.todo.activities.ui;

import com.todo.activities.application.ActivitiesService;
import com.todo.activities.application.commands.CreateNewActivitiesListCommand;
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
    public ResponseEntity<String> addNewActivitiesList(@RequestParam("email") final long userId){
        activitiesService.addNewActivitiesListToUser(
                new CreateNewActivitiesListCommand(userId)
        );
        return new ResponseEntity<>("List created!", HttpStatus.CREATED);
    }

    @RequestMapping(path = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<String> addActivity(){
        return null;
    }

}
