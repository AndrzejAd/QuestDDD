package com.quest.registering.saving.appplication.service;

import com.ddd.common.annotations.ApplicationService;
import com.ddd.common.events.UserIsRegistered;
import com.ddd.common.validation.InvalidCountry;
import com.quest.registering.saving.appplication.commands.CheckAddressCommand;
import com.quest.registering.saving.appplication.commands.CreateUserCommand;
import com.quest.registering.saving.domain.AddressCheckerService;
import com.quest.registering.saving.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
public class RegisterUserService {
    private final AccountRepository accountRepository;
    private final AccountFactory accountFactory;
    private final AddressCheckerService addressCheckerService;
    private final ApplicationEventPublisher applicationEventPublisher;

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
