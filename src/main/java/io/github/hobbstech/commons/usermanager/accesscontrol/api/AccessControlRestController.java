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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/authorities")
    @ApiOperation("Get All system Authorities Paged")
    public Page<Authority> getAllAuthorities(@PageableDefault Pageable pageable, @RequestParam(required = false) String search) {
        return authoritiesReaderService.findAll(pageable, search);
    }

    @GetMapping("/group-authorities/by-group/{groupId}")
    @ApiOperation("Get All Group Authorities Paged")
    public Page<GroupAuthority> getAllGroupAuthorities(@PathVariable long groupId, @PageableDefault Pageable pageable) {
        return groupAuthorityService.findByGroup(groupId, pageable);
    }

    @GetMapping("/group-authorities/by-group/{groupId}/all")
    @ApiOperation("Get All Group Authorities")
    public Collection<GroupAuthority> getAllGroupAuthorities(@PathVariable long groupId) {
        return groupAuthorityService.findByGroup(groupId);
    }

    @PostMapping("/group-authorities")
    @ApiOperation("Create Group Authority")
    public GroupAuthority createGroupAuthority(
            @RequestBody CreateGroupAuthorityCommand createGroupAuthorityCommand) {
        return groupAuthorityService.create(createGroupAuthorityCommand);
    }

    @PostMapping("/group-authorities/bundled")
    @ApiOperation("Create Group Authorities")
    public Collection<GroupAuthority> createGroupAuthorities(
            @RequestBody CreateGroupAuthorityCommand createGroupAuthorityCommand) {
        return groupAuthorityService.createAuthorities(createGroupAuthorityCommand);
    }

    @PatchMapping("/group-authorities/bundled")
    @ApiOperation("Remove Group Authorities")
    public void removeGroupAuthorities(
            @RequestBody Collection<Long> groupAuthorityIds) {
        groupAuthorityService.delete(groupAuthorityIds);
    }

    @GetMapping("/user-authorities/by-user/{userId}/all")
    @ApiOperation("Get All system Authorities")
    public Collection<UserAuthority> getAllUserAuthorities(@PathVariable long userId) {
        return userAuthorityService.findByUser(userId);
    }

    @GetMapping("/user-authorities/by-user/{userId}")
    @ApiOperation("Get All system Authorities")
    public Page<UserAuthority> getAllUserAuthorities(@PathVariable long userId, @PageableDefault Pageable pageable) {
        return userAuthorityService.findByUser(userId, pageable);
    }

    @PostMapping("/user-authorities")
    @ApiOperation("Create User Authority")
    public UserAuthority createUserAuthority(
            @RequestBody CreateUserAuthorityCommand createUserAuthorityCommand) {
        return userAuthorityService.create(createUserAuthorityCommand);
    }

    @PostMapping("/user-authorities/bundled")
    @ApiOperation("Create User Authority")
    public Collection<UserAuthority> createUserAuthorities(
            @RequestBody CreateUserAuthorityCommand createUserAuthorityCommand) {
        return userAuthorityService.createAuthorities(createUserAuthorityCommand);
    }

    @PatchMapping("/user-authorities/bundled")
    @ApiOperation("Remove User Authorities")
    public void removeUserAuthorities(
            @RequestBody Collection<Long> userAuthorityIds) {
        userAuthorityService.delete(userAuthorityIds);
    }

}
