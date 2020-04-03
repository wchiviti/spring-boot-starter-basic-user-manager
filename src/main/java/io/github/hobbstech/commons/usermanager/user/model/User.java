package io.github.hobbstech.commons.usermanager.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.hobbstech.commons.utilities.jpa.BaseEntity;
import io.github.hobbstech.commons.usermanager.group.model.Group;
import io.github.hobbstech.commons.usermanager.user.service.create.CreateUserCommand;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Audited
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    private boolean enabled;

    @ManyToOne(optional = false)
    private Group group;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String initials;

    private String title;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String phoneNumber;

    @Transient
    private Set<GrantedAuthority> authorities;

    private String nationalIdentificationNumber;

    private String passportNumber;

    private String driverLicenseNumber;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "Africa/Harare", locale = "en_ZW")
    private Date dateOfBirth;

    private String nationality;

    public static User fromCommand(CreateUserCommand userCommand) {

        if (userCommand == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userCommand.getUsername());
        user.setFirstName(userCommand.getFirstName());
        user.setLastName(userCommand.getLastName());
        user.setInitials(userCommand.getInitials());
        user.setTitle(userCommand.getTitle());
        user.setGender(userCommand.getGender());
        user.setEmail(userCommand.getEmail());
        user.setEnabled(true);

        user.setNationalIdentificationNumber(userCommand.getNationalIdentificationNumber());
        user.setPassportNumber(userCommand.getPassportNumber());
        user.setDriverLicenseNumber(userCommand.getDriverLicenseNumber());
        user.setDateOfBirth(userCommand.getDateOfBirth());

        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUsername());
    }

    public void addAuthority(SimpleGrantedAuthority authority) {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
        authorities.add(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
