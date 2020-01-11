package io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.dao;

import io.github.hobbstech.commons.utilities.jpa.BaseDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.model.Authority;
import io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.model.GroupAuthority;
import io.github.hobbstech.commons.usermanager.group.model.Group;

import java.util.Collection;

public interface GroupAuthorityDao extends BaseDao<GroupAuthority> {

    Collection<GroupAuthority> findByGroup(Group group);

    Collection<GroupAuthority> findByGroup_Id(long groupId);

    boolean existsByAuthorityAndGroup(Authority authority, Group group);

}
