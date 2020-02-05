package io.github.hobbstech.commons.usermanager.user.service.events;

import io.github.hobbstech.commons.notifications.service.EmailMessageNotifierTemplate;
import io.github.hobbstech.commons.notifications.service.EmailSender;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserAccountStatusChangeEventListener extends EmailMessageNotifierTemplate implements ApplicationListener<UserAccountStatusChangeEvent> {

    @Value("${system.name}")
    private String systemName;

    public UserAccountStatusChangeEventListener(EmailSender emailSender) {
        super(emailSender);
    }

    @Override
    public void onApplicationEvent(UserAccountStatusChangeEvent userAccountStatusChangeEvent) {

        val user = userAccountStatusChangeEvent.getUser();

        if (user.isEnabled()) {
            subject = systemName + " Account enabled";

            emailMessageFormatter.addParagraph("This serves to notify you that your " + systemName +
                    " account has been activate and you can now login to the system and use its facilities");

        } else {
            subject = systemName + " Account disabled";
            emailMessageFormatter.addParagraph("This serves to notify you that your " + systemName +
                    " account has been DISABLED and you can NOT use the system until it is activated");
        }

        sendEmail();

    }
}
