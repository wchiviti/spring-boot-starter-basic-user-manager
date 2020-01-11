package io.github.hobbstech.commons.usermanager.accesscontrol.authorities.service;

import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.Authorities;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.dao.AuthorityDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.model.Authority;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class AuthoritiesPersisterService {

    private final AuthorityDao authorityDao;

    public AuthoritiesPersisterService(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    public void persistAuthorities() {

        Stream.of(Authorities.values())
                .filter(authority -> !authorityDao.existsByName(authority.name()))
                .map(authority -> {
                    val auth = new Authority();
                    auth.setName(authority.name());
                    auth.setDescription(authority.description);
                    return auth;
                }).forEach(authorityDao::save);

    }

}
