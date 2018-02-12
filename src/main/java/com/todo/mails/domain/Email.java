package com.todo.mails.domain;

import com.todo.common.annotations.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@ValueObject
@AllArgsConstructor
@Getter
public class Email {
    private final String to;
    private final String subject;
    private final String text;
    private final LocalDate sendDate;

}
