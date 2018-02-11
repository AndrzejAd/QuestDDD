package com.todo.registering.appplication;

import com.todo.common.annotations.Command;
import com.todo.registering.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Command
@AllArgsConstructor
@Getter
public class CheckAddressCommand {
    private final Address address;
}
