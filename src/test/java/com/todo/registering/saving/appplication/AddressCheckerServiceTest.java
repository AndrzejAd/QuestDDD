package com.todo.registering.saving.appplication;

import com.todo.registering.saving.domain.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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