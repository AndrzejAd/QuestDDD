package com.todo.registering.appplication;

import com.todo.registering.domain.Address;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AddressCheckerServiceTest {

    @Test
    void shouldReturnTrue() {
        Address address = new Address("poland", "warsaw");
        CheckAddressCommand checkAddressCommand = new CheckAddressCommand(address);
        AddressCheckerService addressCheckerService = new AddressCheckerService();
        assertTrue(addressCheckerService.checkIfCountryExists(checkAddressCommand));
    }

    @Test
    void shouldReturnFalse() {
        Address address = new Address("polasdasdand", "warsaw");
        CheckAddressCommand checkAddressCommand = new CheckAddressCommand(address);
        AddressCheckerService addressCheckerService = new AddressCheckerService();
        assertFalse(addressCheckerService.checkIfCountryExists(checkAddressCommand));
    }

}