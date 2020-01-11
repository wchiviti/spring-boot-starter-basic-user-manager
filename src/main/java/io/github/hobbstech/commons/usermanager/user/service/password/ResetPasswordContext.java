package io.github.hobbstech.commons.usermanager.user.service.password;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordContext {

    @NotBlank(message = "Token must be provided")
    private String token;

    @NotBlank(message = "Password must be provided")
    private String password;

    @NotBlank(message = "Confirm password must be provided")
    private String confirmPassword;

}
