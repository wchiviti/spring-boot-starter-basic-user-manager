package io.github.hobbstech.commons.usermanager.user.service.events;

import io.github.hobbstech.commons.usermanager.user.model.Token;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ForgotPasswordEvent extends ApplicationEvent {

    private final Token token;

    public ForgotPasswordEvent(Object source, Token token) {
        super(source);
        this.token = token;
    }
}
