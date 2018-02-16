package com.todo.registering.saving.appplication.commands;

import com.ddd.common.annotations.Command;
import com.todo.registering.saving.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Command
@AllArgsConstructor
@Getter
public class CheckAddressCommand {
    private final Address address;
}
