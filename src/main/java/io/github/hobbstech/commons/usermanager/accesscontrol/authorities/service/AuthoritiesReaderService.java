package io.github.hobbstech.commons.usermanager.accesscontrol.authorities.service;

import io.github.hobbstech.commons.utilities.service.AbstractReaderService;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.dao.AuthorityDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.model.Authority;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesReaderService extends AbstractReaderService<Authority> {

    public AuthoritiesReaderService(AuthorityDao authorityDao) {
        super(authorityDao);
    }
}
