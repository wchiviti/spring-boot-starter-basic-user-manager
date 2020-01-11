package io.github.hobbstech.commons.usermanager.user.api;

import io.github.hobbstech.commons.utilities.exceptions.AccessDeniedException;
import io.github.hobbstech.commons.usermanager.user.model.User;
import io.github.hobbstech.commons.usermanager.user.service.UserReaderService;
import io.github.hobbstech.commons.usermanager.user.service.create.CreateUserCommand;
import io.github.hobbstech.commons.usermanager.user.service.create.CreateUserService;
import io.github.hobbstech.commons.usermanager.user.service.update.UpdateUserCommand;
import io.github.hobbstech.commons.usermanager.user.service.update.UpdateUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

import static java.util.Objects.isNull;

@RestController
@Api(tags = "Users")
public class UsersRestController {

    private final CreateUserService createUserService;

    private final UserReaderService userReaderService;

    private final UpdateUserService updateUserService;

    public UsersRestController(CreateUserService createUserService, UserReaderService userReaderService,
                               UpdateUserService updateUserService) {
        this.createUserService = createUserService;
        this.userReaderService = userReaderService;
        this.updateUserService = updateUserService;
    }

    @PostMapping("/v1/users")
    @ApiOperation("Create User")
    public User createUser(@RequestBody CreateUserCommand createUserCommand) {
        return createUserService.execute(createUserCommand);
    }

    @PutMapping("/v1/users/{userId}")
    @ApiOperation("Update User Admin")
    public User updateUser(@RequestBody UpdateUserCommand updateUserCommand, @PathVariable long userId) {
        updateUserCommand.setId(userId);
        return updateUserService.update(updateUserCommand);
    }

    @PatchMapping("/v1/users/{userId}")
    @ApiOperation("Disable or Enable User User Admin")
    public User changeUserStatus(@RequestParam boolean status, @PathVariable long userId) {
        return updateUserService.changeUserStatus(userId, status);
    }

    @PostMapping("/v1/users/my-account")
    @ApiOperation("Update My User Account")
    public User updateMyUserAccount(@RequestBody UpdateUserCommand updateUserCommand, Principal principal) {
        if (isNull(principal)) {
            throw new AccessDeniedException("Only logged in person can perform this task");
        }
        updateUserCommand.setUsername(principal.getName());
        return updateUserService.updateMyAccount(updateUserCommand);
    }

    @GetMapping("/v1/users/all")
    @ApiOperation("Get All Users")
    public Collection<User> getAllUsers() {
        return userReaderService.findAll();
    }

    @GetMapping("/v1/users/{userId}")
    @ApiOperation("Get one User")
    public User getOneById(@PathVariable long userId) {
        return userReaderService.findById(userId);
    }

    @GetMapping("/v1/v")
    @ApiOperation("Get All Paginated Users")
    public Page<User> getAllPaginatedUsers(
            @RequestParam(value = "sort", required = false, defaultValue = "username") String sort,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "order", required = false, defaultValue = "ASC") String direction,
            @RequestParam(name = "search", required = false) String search) {

        val pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        return userReaderService.findAll(pageable, search);

    }


}
