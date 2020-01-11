package io.github.hobbstech.commons.usermanager.user.service.password;

import lombok.Data;

@Data
public class ForgotPasswordContext {

    private String email;

    private String username;

}
