package io.github.hobbstech.commons.usermanager.accesscontrol.userauthorities.dao;

import io.github.hobbstech.commons.utilities.jpa.BaseDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.model.Authority;
import io.github.hobbstech.commons.usermanager.accesscontrol.userauthorities.model.UserAuthority;
import io.github.hobbstech.commons.usermanager.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface UserAuthorityDao extends BaseDao<UserAuthority> {

    Collection<UserAuthority> findByUser(User user);

    Collection<UserAuthority> findByUser_Id(long userId);

    boolean existsByAuthorityAndUser(Authority authority, User user);

    Page<UserAuthority> findByUser_Id(long userId, Pageable pageable);
}
