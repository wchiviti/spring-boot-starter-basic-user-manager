package io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.service;

import lombok.Data;

@Data
public class CreateGroupAuthorityCommand {

    private long groupId;

    private long authorityId;

}
