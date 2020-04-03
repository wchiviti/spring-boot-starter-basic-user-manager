package io.github.hobbstech.commons.usermanager.group.service;

import io.github.hobbstech.commons.usermanager.group.dao.GroupDao;
import io.github.hobbstech.commons.usermanager.group.model.Group;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Collection;

@Slf4j
public abstract class GroupsInitializationStrategy {

    protected final GroupDao groupDao;

    protected GroupsInitializationStrategy(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    protected void persistAuthorities(Collection<String> groups) {
        groups.forEach(groupName -> {
            val exists = groupDao.existsByName(groupName);
            if (exists) {
                return;
            }
            val authority = new Group();
            authority.setName(groupName);
            groupDao.save(authority);
            log.debug("### Group created : {}", groupName);
        });
    }

}
