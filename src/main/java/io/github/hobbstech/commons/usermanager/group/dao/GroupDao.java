package io.github.hobbstech.commons.usermanager.group.dao;

import io.github.hobbstech.commons.utilities.jpa.BaseDao;
import io.github.hobbstech.commons.usermanager.group.model.Group;

import java.util.Optional;

public interface GroupDao extends BaseDao<Group> {

    Optional<Group> findByName(String name);

    boolean existsByName(String name);

}
