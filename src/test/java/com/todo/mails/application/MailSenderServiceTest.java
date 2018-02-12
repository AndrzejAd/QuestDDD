package com.todo.mails.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MailSenderServiceTest {

    @Autowired
    MailSenderService mailSenderService;

    @Test
    public void shouldSendMail(){
        mailSenderService.sendEmail("", "", "");
    }


}