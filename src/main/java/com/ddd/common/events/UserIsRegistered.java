package com.ddd.common.events;

import com.ddd.common.annotations.Event;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

@Event
@Getter
public class UserIsRegistered extends ApplicationEvent {
    private final long userId;
    private final String email;
    private final LocalDate registerDate;
    private final String username;

    public UserIsRegistered(Object source, long userId, String email, LocalDate subscribeDate, String username) {
        super(source);
        this.userId = userId;
        this.email = email;
        this.registerDate = subscribeDate;
        this.username = username;
    }
}
