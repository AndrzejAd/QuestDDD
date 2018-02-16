package com.todo.registering.saving.appplication;

import com.ddd.common.annotations.ApplicationService;
import com.ddd.common.events.UserIsRegistered;
import com.ddd.common.validation.InvalidCountry;
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
    private final AccountRepository accountRepository;
    private final AccountFactory accountFactory;
    private final AddressCheckerService addressCheckerService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public RegisterUserService(AccountRepository accountRepository, AccountFactory accountFactory,
                               AddressCheckerService addressCheckerService, ApplicationEventPublisher applicationEventPublisher) {
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
        this.addressCheckerService = addressCheckerService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void registerUser(CreateUserCommand createUserCommand) {
        Address address = new Address(createUserCommand.getCountry(), createUserCommand.getCity());
        if (addressCheckerService.checkIfCountryExists(new CheckAddressCommand(address))) {
            Account account = accountFactory.createUser(
                    createUserCommand.getEmailAddress(),
                    createUserCommand.getCountry(),
                    createUserCommand.getCity(),
                    createUserCommand.getUsername(),
                    createUserCommand.getPassword(),
                    createUserCommand.getEpochBirthDate()
            );
            accountRepository.save(account);
            UserIsRegistered userIsRegistered = new UserIsRegistered(
                    this, account.getId(), account.getEmail().getAddress(),
                    account.getCreationDate(), account.getUsername());
            applicationEventPublisher.publishEvent(userIsRegistered);
        } else {
            throw new InvalidCountry();
        }

    }

}
