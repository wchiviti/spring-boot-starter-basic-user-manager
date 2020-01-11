package io.github.hobbstech.commons.usermanager.accesscontrol.authorities.dao;

import io.github.hobbstech.commons.utilities.jpa.BaseDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.model.Authority;

public interface AuthorityDao extends BaseDao<Authority> {

    boolean existsByName(String name);

}
