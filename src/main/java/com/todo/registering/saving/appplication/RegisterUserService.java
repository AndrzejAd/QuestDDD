package com.todo.registering.saving.appplication;

import com.todo.common.annotations.ApplicationService;
import com.todo.common.validation.InvalidCountry;
import com.todo.registering.saving.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
public class RegisterUserService {
    private final UsersRepository usersRepository;
    private final UserFactory userFactory;
    private final AddressCheckerService addressCheckerService;

    @Autowired
    public RegisterUserService(UsersRepository usersRepository, UserFactory userFactory, AddressCheckerService addressCheckerService) {
        this.usersRepository = usersRepository;
        this.userFactory = userFactory;
        this.addressCheckerService = addressCheckerService;
    }

    @Transactional( propagation  = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void registerUser(CreateUserCommand createUserCommand){
        Address address = new Address(createUserCommand.getCountry(), createUserCommand.getCity());
        if ( addressCheckerService.checkIfCountryExists(new CheckAddressCommand(address)) ){
            User user = userFactory.createUser(
                    createUserCommand.getEmailAddress(),
                    createUserCommand.getCountry(),
                    createUserCommand.getCity(),
                    createUserCommand.getUsername(),
                    createUserCommand.getPassword(),
                    createUserCommand.getEpochBirthDate()
            );
            usersRepository.save(user);
        } else{
            throw new InvalidCountry();
        }

    }

}