package io.github.hobbstech.commons.usermanager.group.api;

import io.github.hobbstech.commons.usermanager.group.model.Group;
import io.github.hobbstech.commons.usermanager.group.service.CreateGroupCommand;
import io.github.hobbstech.commons.usermanager.group.service.GroupService;
import io.github.hobbstech.commons.usermanager.group.service.UpdateGroupCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/v1/groups")
@Api(tags = "Groups")
public class GroupRestController {

    private final GroupService groupService;

    public GroupRestController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ApiOperation("Get All Groups")
    @GetMapping("/all")
    public Collection<Group> getAllGroupCredentials() {
        return groupService.findAll();
    }

    @ApiOperation("Get Paginated Groups")
    @GetMapping
    public Page<Group> getPaginatedGroupCredentials(
            @PageableDefault(size = 20, sort = "name") Pageable pageable,
            @RequestParam(value = "search", required = false) String search) {
        return groupService.findAll(pageable, search);
    }

    @ApiOperation("Get Group")
    @GetMapping("/{groupId}")
    public Group getPaginatedGroupCredentials(
            @PathVariable("groupId") Long groupId) {
        return groupService.findById(groupId);
    }

    @ApiOperation("Create Group")
    @PostMapping
    public Group create(
            @RequestBody CreateGroupCommand createGroupCommand) {
        return groupService.create(createGroupCommand);
    }

    @ApiOperation("Update Group")
    @PutMapping("/{groupId}")
    public Group update(
            @PathVariable("groupId") Long groupId,
            @RequestBody UpdateGroupCommand updateGroupCommand) {
        updateGroupCommand.setId(groupId);
        return groupService.update(updateGroupCommand);
    }

    @ApiOperation("Delete Group")
    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable("groupId") Long groupId) {
        groupService.delete(groupId);
    }

}
