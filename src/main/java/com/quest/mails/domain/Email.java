package com.quest.mails.domain;

import com.ddd.common.validation.Contract;
import lombok.Value;

import java.time.LocalDate;

@Value
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
