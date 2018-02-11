package com.todo.registering.appplication;

import com.todo.common.annotations.ApplicationService;
import com.todo.registering.domain.Address;
import com.todo.registering.domain.User;
import com.todo.registering.domain.UserFactory;
import com.todo.registering.domain.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

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
        CheckAddressCommand checkAddressCommand = new CheckAddressCommand(address);
        CompletableFuture<Boolean> isAddressValid
                = CompletableFuture.supplyAsync( () -> addressCheckerService.checkIfCountryExists(checkAddressCommand));
        User user = userFactory.createUser(
                createUserCommand.getEmailAddress(),
                createUserCommand.getCountry(),
                createUserCommand.getCity(),
                createUserCommand.getUsername(),
                createUserCommand.getPassword(),
                createUserCommand.getEpochBirthDate()
        );
    }

}
