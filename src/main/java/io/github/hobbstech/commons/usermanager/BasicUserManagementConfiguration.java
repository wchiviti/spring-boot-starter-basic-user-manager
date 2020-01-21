package io.github.hobbstech.commons.usermanager;

import io.github.hobbstech.commons.utilities.util.SystemProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "io.github.hobbstech.commons.usermanager")
@EnableJpaRepositories(basePackages = "io.github.hobbstech.commons.usermanager")
@EntityScan(basePackages = "io.github.hobbstech.commons.usermanager")
public class BasicUserManagementConfiguration {
}
