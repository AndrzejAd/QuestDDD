package com.todo.registering.saving.ui;

import com.todo.registering.saving.appplication.commands.CreateUserCommand;
import com.todo.registering.saving.appplication.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final RegisterUserService registerUserService;

    @Autowired
    public UserRestController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(
            @RequestParam("email") final String emailAddress,
            @RequestParam("country") final String country,
            @RequestParam("city") final String city,
            @RequestParam("username") final String username,
            @RequestParam("password") final String password,
            @RequestParam("epochBirthDate") final long birthDate
    ) {
        CreateUserCommand createUserCommand = new CreateUserCommand(
                emailAddress,
                country,
                city,
                username,
                password,
                birthDate
        );
        registerUserService.registerUser(createUserCommand);
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }

}
