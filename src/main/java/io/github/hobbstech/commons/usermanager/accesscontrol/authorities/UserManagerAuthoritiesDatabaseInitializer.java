package io.github.hobbstech.commons.usermanager.accesscontrol.authorities;

import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.dao.AuthorityDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.service.AuthoritiesInitializationStrategy;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Configuration
@Slf4j
public class UserManagerAuthoritiesDatabaseInitializer extends AuthoritiesInitializationStrategy implements InitializingBean {

    public UserManagerAuthoritiesDatabaseInitializer(AuthorityDao authorityDao) {
        super(authorityDao);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        val authorities = Stream.of(Authorities.values()).map(Enum::name).collect(toSet());

        persistAuthorities(authorities);

    }

}
