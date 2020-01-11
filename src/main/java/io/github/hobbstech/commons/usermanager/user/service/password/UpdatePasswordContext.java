package io.github.hobbstech.commons.usermanager.user.service.password;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePasswordContext {

    @JsonIgnore
    @NotBlank(message = "Username should be provided")
    private String username;

    @NotBlank(message = "Old password should be provided")
    private String oldPassword;

    @NotBlank(message = "New Password should be provided")
    private String password;

    @NotBlank(message = "Confirm new Password should be provided")
    private String confirmPassword;

}
