package io.github.hobbstech.commons.usermanager;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "io.github.hobbstech.commons.usermanager")
@EnableJpaRepositories(basePackages = "io.github.hobbstech.commons.usermanager")
@EntityScan(basePackages = "io.github.hobbstech.commons.usermanager")
public class BasicUserManagementConfiguration {
}
