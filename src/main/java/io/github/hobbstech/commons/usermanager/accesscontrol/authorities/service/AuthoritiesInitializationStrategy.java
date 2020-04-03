package io.github.hobbstech.commons.usermanager.accesscontrol.authorities.service;

import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.dao.AuthorityDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.model.Authority;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Collection;

@Slf4j
public abstract class AuthoritiesInitializationStrategy {

    protected final AuthorityDao authorityDao;

    protected AuthoritiesInitializationStrategy(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    protected void persistAuthorities(Collection<String> authorities) {
        authorities.forEach(authorityName -> {
                    val exists = authorityDao.existsByName(authorityName);
                    if (exists) {
                        return;
                    }
                    val authority = new Authority();
                    authority.setDescription(authorityName);
                    authority.setName(authorityName);
                    authorityDao.save(authority);
                    log.debug("### Authority created : {}", authorityName);
                });
    }

}
