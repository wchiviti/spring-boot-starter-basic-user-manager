package io.github.hobbstech.commons.usermanager.user.service.events;

import io.github.hobbstech.commons.utilities.util.SystemProperties;
import io.github.hobbstech.commons.notifications.service.EmailMessageNotifierTemplate;
import io.github.hobbstech.commons.notifications.service.EmailSender;
import lombok.val;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserAccountStatusChangeEventListener extends EmailMessageNotifierTemplate implements ApplicationListener<UserAccountStatusChangeEvent> {

    private final SystemProperties systemProperties;

    public UserAccountStatusChangeEventListener(EmailSender emailSender, SystemProperties systemProperties) {
        super(emailSender);
        this.systemProperties = systemProperties;
    }

    @Override
    public void onApplicationEvent(UserAccountStatusChangeEvent userAccountStatusChangeEvent) {

        val user = userAccountStatusChangeEvent.getUser();

        if (user.isEnabled()) {
            subject = systemProperties.systemName + " Account enabled";

            emailMessageFormatter.addParagraph("This serves to notify you that your " + systemProperties.systemName +
                    " account has been activate and you can now login to the system and use its facilities");

        } else {
            subject = systemProperties.systemName + " Account disabled";
            emailMessageFormatter.addParagraph("This serves to notify you that your " + systemProperties.systemName +
                    " account has been DISABLED and you can NOT use the system until it is activated");
        }

        sendEmail();

    }
}
