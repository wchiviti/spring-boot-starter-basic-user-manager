package io.github.hobbstech.commons.usermanager.user.service.create;

import io.github.hobbstech.commons.utilities.exceptions.InvalidRequestException;
import io.github.hobbstech.commons.utilities.service.SingleResponsibilityActioningService;
import io.github.hobbstech.commons.utilities.exceptions.InvalidRequestException;
import io.github.hobbstech.commons.utilities.service.SingleResponsibilityActioningService;
import io.github.hobbstech.commons.usermanager.group.service.GroupService;
import io.github.hobbstech.commons.usermanager.user.dao.UserDao;
import io.github.hobbstech.commons.usermanager.user.model.User;
import io.github.hobbstech.commons.usermanager.user.service.events.NewUserEvent;
import io.github.hobbstech.commons.usermanager.user.service.password.PasswordGenerator;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.hobbstech.commons.utilities.validations.Validations.validate;
import static io.github.hobbstech.commons.utilities.validations.Validations.validate;

@Service
@Transactional
public class CreateUserService implements SingleResponsibilityActioningService<User, CreateUserCommand> {

    private final PasswordEncoder passwordEncoder;

    private final PasswordGenerator passwordGenerator;

    private final UserDao userDao;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final GroupService groupService;

    public CreateUserService(PasswordEncoder passwordEncoder,
                             PasswordGenerator passwordGenerator,
                             UserDao userDao, ApplicationEventPublisher applicationEventPublisher,
                             GroupService groupService) {
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.userDao = userDao;
        this.applicationEventPublisher = applicationEventPublisher;
        this.groupService = groupService;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('CREATE_USER') or hasRole('ROLE_ADMIN')")
    public User execute(CreateUserCommand createUserCommand) {

        validate(createUserCommand);

        if (userDao.existsByUsername(createUserCommand.getUsername())) {
            throw new InvalidRequestException("User with the same username [" + createUserCommand.getUsername() + "] " +
                    "already exist");
        }

        if (userDao.existsByEmail(createUserCommand.getEmail())) {
            throw new InvalidRequestException("User with the same email [" + createUserCommand.getEmail() + "] " +
                    "already exist");
        }

        val group = groupService.findById(createUserCommand.getGroupId());

        val user = User.fromCommand(createUserCommand);

        val password = passwordGenerator.generate();

        user.setPassword(passwordEncoder.encode(password));

        user.setGroup(group);

        val persistedUser = userDao.save(user);

        val newUserEvent = new NewUserEvent(this, persistedUser, password);

        applicationEventPublisher.publishEvent(newUserEvent);

        return persistedUser;
    }
}
