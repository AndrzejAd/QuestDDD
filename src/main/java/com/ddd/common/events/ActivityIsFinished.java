package com.ddd.common.events;

import com.ddd.common.annotations.Event;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import java.time.LocalDateTime;

@Event
@Getter
public class ActivityIsFinished extends ApplicationEvent {
    private final long id;
    private final String email;
    private final String username;
    private final LocalDateTime finishTime;
    private final long xpGained;

    public ActivityIsFinished(Object source, long id, String email, String username, LocalDateTime finishTime, long xpGained) {
        super(source);
        this.id = id;
        this.email = email;
        this.username = username;
        this.finishTime = finishTime;
        this.xpGained = xpGained;
    }
}
