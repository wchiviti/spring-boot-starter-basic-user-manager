package io.github.hobbstech.commons.usermanager.user.service.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.hobbstech.commons.usermanager.user.model.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateUserCommand {

    @JsonIgnore
    private long id;

    @JsonIgnore
    private String username;

    @NotBlank(message = "First name should be provided")
    private String firstName;

    @NotBlank(message = "Last name should be provided")
    private String lastName;

    private String initials;

    private String title;

    @NotNull(message = "Gender should be provided")
    private Gender gender;

    @NotBlank(message = "Email should be provided")
    @Email(message = "A valid email should be provided")
    private String email;

    private String nationalIdentificationNumber;

    private String passportNumber;

    private String driverLicenseNumber;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, locale = "en_ZW", timezone = "Africa/Harare")
    private Date dateOfBirth;

    private String phoneNumber;

    private String nationality;

}
