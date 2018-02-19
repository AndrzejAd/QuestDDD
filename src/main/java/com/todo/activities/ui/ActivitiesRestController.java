package com.todo.activities.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitiesRestController {

    @RequestMapping(path = "/createNewActivities", method = RequestMethod.POST)
    public ResponseEntity<String> createNewActivities(){
        return null;
    }

    @RequestMapping(path = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<String> addActivity(){
        return null;
    }

}
