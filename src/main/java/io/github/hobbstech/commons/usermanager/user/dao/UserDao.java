package io.github.hobbstech.commons.usermanager.user.dao;

import io.github.hobbstech.commons.usermanager.user.model.User;
import io.github.hobbstech.commons.utilities.jpa.BaseDao;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
