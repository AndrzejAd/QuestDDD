package com.todo.location.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationRestController {

    @RequestMapping(path = "/getNearbyUsers", method = RequestMethod.GET)
    public ResponseEntity<String> addNewActivitiesList(@RequestParam("userId") final long userId){

        return new ResponseEntity<>("List created!", HttpStatus.CREATED);
    }

}
