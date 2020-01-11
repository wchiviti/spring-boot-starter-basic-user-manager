package io.github.hobbstech.commons.usermanager.accesscontrol.api;

import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.model.Authority;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.service.AuthoritiesReaderService;
import io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.model.GroupAuthority;
import io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.service.CreateGroupAuthorityCommand;
import io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.service.GroupAuthorityService;
import io.github.hobbstech.commons.usermanager.accesscontrol.userauthorities.model.UserAuthority;
import io.github.hobbstech.commons.usermanager.accesscontrol.userauthorities.service.CreateUserAuthorityCommand;
import io.github.hobbstech.commons.usermanager.accesscontrol.userauthorities.service.UserAuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/access-control")
@Api(tags = "Access Control")
public class AccessControlRestController {

    private final AuthoritiesReaderService authoritiesReaderService;

    private final GroupAuthorityService groupAuthorityService;

    private final UserAuthorityService userAuthorityService;

    public AccessControlRestController(AuthoritiesReaderService authoritiesReaderService,
                                       GroupAuthorityService groupAuthorityService,
                                       UserAuthorityService userAuthorityService) {
        this.authoritiesReaderService = authoritiesReaderService;
        this.groupAuthorityService = groupAuthorityService;
        this.userAuthorityService = userAuthorityService;
    }

    @GetMapping("/authorities/all")
    @ApiOperation("Get All system Authorities")
    public Collection<Authority> getAllAuthorities() {
        return authoritiesReaderService.findAll();
    }

    @GetMapping("/group-authorities/by-group/{groupId}")
    @ApiOperation("Get All system Authorities")
    public Collection<GroupAuthority> getAllGroupAuthorities(@PathVariable long groupId) {
        return groupAuthorityService.findByGroup(groupId);
    }

    @PostMapping("/group-authorities")
    @ApiOperation("Create Group Authority")
    public GroupAuthority createGroupAuthority(
            @RequestBody CreateGroupAuthorityCommand createGroupAuthorityCommand) {
        return groupAuthorityService.create(createGroupAuthorityCommand);
    }

    @GetMapping("/user-authorities/by-user/{userId}")
    @ApiOperation("Get All system Authorities")
    public Collection<UserAuthority> getAllUserAuthorities(@PathVariable long userId) {
        return userAuthorityService.findByUser(userId);
    }

    @PostMapping("/user-authorities")
    @ApiOperation("Create User Authority")
    public UserAuthority createUserAuthority(
            @RequestBody CreateUserAuthorityCommand createUserAuthorityCommand) {
        return userAuthorityService.create(createUserAuthorityCommand);
    }

}
