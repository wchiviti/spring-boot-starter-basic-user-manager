package io.github.hobbstech.commons.usermanager.user.service.events;

import io.github.hobbstech.commons.utilities.util.SystemProperties;
import io.github.hobbstech.commons.notifications.service.EmailMessageFormatter;
import io.github.hobbstech.commons.notifications.service.EmailMessageNotifierTemplate;
import io.github.hobbstech.commons.notifications.service.EmailSender;
import io.github.hobbstech.commons.notifications.service.EmailUserImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ForgotPasswordEventListener extends EmailMessageNotifierTemplate
        implements ApplicationListener<ForgotPasswordEvent> {

    private final SystemProperties systemProperties;

    public ForgotPasswordEventListener(EmailSender emailSender, SystemProperties systemProperties) {
        super(emailSender);
        this.systemProperties = systemProperties;
    }

    @Override
    public void onApplicationEvent(ForgotPasswordEvent forgotPasswordEvent) {

        val token = forgotPasswordEvent.getToken();

        val user = token.getUser();

        recipients.add(new EmailUserImpl(user.getUsername(), user.getEmail()));

        subject = systemProperties.systemName + " : Forgot password token";

        emailMessageFormatter.addParagraph("Please use the following token to proceed in resetting " +
                "your password");

        emailMessageFormatter.addParagraph(EmailMessageFormatter.boldText("Token : ") + token.getValue());
        emailMessageFormatter.addParagraph(EmailMessageFormatter.boldText("Token expired on : ") +
                DateTimeFormatter.RFC_1123_DATE_TIME.format(token.getExpiryDate().toInstant()));

        sendEmail();

    }
}
