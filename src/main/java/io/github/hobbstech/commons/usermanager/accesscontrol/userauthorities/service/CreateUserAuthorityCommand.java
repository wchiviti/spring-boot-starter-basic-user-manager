package io.github.hobbstech.commons.usermanager.accesscontrol.userauthorities.service;

import lombok.Data;

@Data
public class CreateUserAuthorityCommand {

    private long userId;

    private long authorityId;

}
