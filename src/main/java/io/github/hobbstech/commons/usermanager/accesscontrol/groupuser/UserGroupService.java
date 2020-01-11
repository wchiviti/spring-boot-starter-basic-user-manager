package io.github.hobbstech.commons.usermanager.accesscontrol.groupuser;

import io.github.hobbstech.commons.utilities.exceptions.InvalidRequestException;
import io.github.hobbstech.commons.utilities.exceptions.RecordNotFoundException;
import io.github.hobbstech.commons.usermanager.group.dao.GroupDao;
import io.github.hobbstech.commons.usermanager.user.dao.UserDao;
import io.github.hobbstech.commons.usermanager.user.model.User;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserGroupService {

    private final UserDao userDao;

    private final GroupDao groupDao;


    public UserGroupService(UserDao userDao, GroupDao groupDao) {
        this.userDao = userDao;
        this.groupDao = groupDao;
    }

    @PreAuthorize("hasAuthority('CREATE_GROUP_USER')")
    public User changeUserGroup(UserGroupCommand userGroupCommand) {

        val user = userDao.findById(userGroupCommand.getUserId())
                .orElseThrow(() -> new RecordNotFoundException("User record was not found"));

        val group = groupDao.findById(userGroupCommand.getGroupId())
                .orElseThrow(() -> new RecordNotFoundException("Group record was not found"));

        if (user.getGroup().equals(group)) {
            throw new InvalidRequestException("User already assigned to group");
        }

        user.setGroup(group);

        return userDao.save(user);

    }

}
