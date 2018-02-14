package com.todo.registering.saving.appplication;

import com.todo.common.annotations.ApplicationService;
import com.todo.common.events.UserIsRegistered;
import com.todo.common.validation.InvalidCountry;
import com.todo.registering.saving.appplication.commands.CheckAddressCommand;
import com.todo.registering.saving.appplication.commands.CreateUserCommand;
import com.todo.registering.saving.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
public class RegisterUserService {
    private final UsersRepository usersRepository;
    private final UserFactory userFactory;
    private final AddressCheckerService addressCheckerService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public RegisterUserService(UsersRepository usersRepository, UserFactory userFactory,
                               AddressCheckerService addressCheckerService, ApplicationEventPublisher applicationEventPublisher) {
        this.usersRepository = usersRepository;
        this.userFactory = userFactory;
        this.addressCheckerService = addressCheckerService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void registerUser(CreateUserCommand createUserCommand) {
        Address address = new Address(createUserCommand.getCountry(), createUserCommand.getCity());
        if (addressCheckerService.checkIfCountryExists(new CheckAddressCommand(address))) {
            User user = userFactory.createUser(
                    createUserCommand.getEmailAddress(),
                    createUserCommand.getCountry(),
                    createUserCommand.getCity(),
                    createUserCommand.getUsername(),
                    createUserCommand.getPassword(),
                    createUserCommand.getEpochBirthDate()
            );
            usersRepository.save(user);
            UserIsRegistered userIsRegistered = new UserIsRegistered(
                    this, user.getId(), user.getEmail().getAddress(),
                    user.getCreationDate(), user.getUsername());
            applicationEventPublisher.publishEvent(userIsRegistered);
        } else {
            throw new InvalidCountry();
        }

    }

}
