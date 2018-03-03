package com.quest.registering.saving.appplication;

import com.quest.registering.saving.appplication.commands.CheckAddressCommand;
import com.quest.registering.saving.appplication.internal.AddressCheckerService;
import com.quest.registering.saving.domain.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressCheckerServiceTest {

    @Test
    void shouldReturnTrue() {
        // given
        Address address = new Address("poland", "warsaw");
        CheckAddressCommand checkAddressCommand = new CheckAddressCommand(address);
        AddressCheckerService addressCheckerService = new AddressCheckerService();
        //then
        assertTrue(addressCheckerService.checkIfCountryExists(checkAddressCommand));
    }

    @Test
    void shouldReturnFalse() {
        // given
        Address address = new Address("polasdasdand", "warsaw");
        CheckAddressCommand checkAddressCommand = new CheckAddressCommand(address);
        AddressCheckerService addressCheckerService = new AddressCheckerService();
        //then
        assertFalse(addressCheckerService.checkIfCountryExists(checkAddressCommand));
    }

}