package com.todo.registering.mails.domain;

import com.todo.common.annotations.ValueObject;
import com.todo.common.validation.Contract;
import lombok.Getter;

import java.time.LocalDate;

@ValueObject
@Getter
public class Email {
    private final String address;
    private final String subject;
    private final String text;
    private final LocalDate sendDate;

    public Email(String address, String subject, String text, String username) {
        Contract.notNull(address, subject, text);
        Contract.matches(address, ".*@.*");
        this.address = address;
        this.subject = subject;
        this.text = "Hello " + username + " \n, " + text;
        this.sendDate = LocalDate.now();
    }


}
