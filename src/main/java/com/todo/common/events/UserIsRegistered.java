package com.todo.common.events;

import com.todo.common.annotations.Event;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

@Event
@Getter
public class UserIsRegistered extends ApplicationEvent {
    private final long userId;
    private final String email;
    private final LocalDate registerDate;

    public UserIsRegistered(Object source, long userId, String email, LocalDate subscribeDate) {
        super(source);
        this.userId = userId;
        this.email = email;
        this.registerDate = subscribeDate;
    }
}
