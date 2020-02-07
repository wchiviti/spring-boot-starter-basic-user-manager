package io.github.hobbstech.commons.usermanager.accesscontrol.userauthorities.service;

import lombok.Data;

import java.util.Collection;

@Data
public class CreateUserAuthorityCommand {

    private long userId;

    private long authorityId;

    private Collection<Long> authorityIds;

}
