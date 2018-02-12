package com.todo.registering.saving.appplication;

import com.todo.common.annotations.Command;
import com.todo.registering.saving.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Command
@AllArgsConstructor
@Getter
public class CheckAddressCommand {
    private final Address address;
}
