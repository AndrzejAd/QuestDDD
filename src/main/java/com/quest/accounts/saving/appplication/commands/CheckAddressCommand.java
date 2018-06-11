package com.quest.accounts.saving.appplication.commands;

import com.ddd.common.annotations.Command;
import com.quest.accounts.saving.domain.Address;
import lombok.Value;

@Command
@Value
public class CheckAddressCommand {
    private final Address address;
}
