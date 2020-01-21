package io.github.hobbstech.commons.usermanager;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import(BasicUserManagementConfiguration.class)
public @interface BasicUserManagementAware {
}
