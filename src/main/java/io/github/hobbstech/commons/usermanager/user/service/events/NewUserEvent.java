package io.github.hobbstech.commons.usermanager.user.service.events;

import io.github.hobbstech.commons.usermanager.user.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewUserEvent extends ApplicationEvent {

    private final User user;

    private final String rawPassword;

    public NewUserEvent(Object source, User user, String rawPassword) {
        super(source);
        this.user = user;
        this.rawPassword = rawPassword;
    }
}
