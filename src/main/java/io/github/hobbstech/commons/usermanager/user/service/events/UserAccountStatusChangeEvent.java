package io.github.hobbstech.commons.usermanager.user.service.events;

import io.github.hobbstech.commons.usermanager.user.model.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserAccountStatusChangeEvent extends ApplicationEvent {

    private final User user;

    public UserAccountStatusChangeEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
