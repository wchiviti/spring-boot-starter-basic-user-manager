package io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.service;

import io.github.hobbstech.commons.usermanager.accesscontrol.authorities.dao.AuthorityDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.dao.GroupAuthorityDao;
import io.github.hobbstech.commons.usermanager.accesscontrol.groupauthorities.model.GroupAuthority;
import io.github.hobbstech.commons.usermanager.group.dao.GroupDao;
import io.github.hobbstech.commons.utilities.exceptions.InvalidRequestException;
import io.github.hobbstech.commons.utilities.exceptions.RecordNotFoundException;
import io.github.hobbstech.commons.utilities.service.DomainServiceImpl;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

import static io.github.hobbstech.commons.utilities.validations.Validations.validate;

@Service
@Transactional
public class GroupAuthorityService extends DomainServiceImpl<GroupAuthority, CreateGroupAuthorityCommand,
        GroupAuthority> {

    private final AuthorityDao authorityDao;

    private final GroupDao groupDao;

    private final GroupAuthorityDao groupAuthorityDao;

    public GroupAuthorityService(GroupAuthorityDao groupAuthorityDao, AuthorityDao authorityDao, GroupDao groupDao) {
        super(groupAuthorityDao);
        this.authorityDao = authorityDao;
        this.groupDao = groupDao;
        this.groupAuthorityDao = groupAuthorityDao;
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_GROUP_AUTHORITY')")
    public GroupAuthority create(CreateGroupAuthorityCommand createGroupAuthorityCommand) {

        validate(createGroupAuthorityCommand);

        val group = groupDao.findById(createGroupAuthorityCommand.getGroupId())
                .orElseThrow(() -> new RecordNotFoundException("Group record was not found"));

        val authority = authorityDao.findById(createGroupAuthorityCommand.getAuthorityId())
                .orElseThrow(() -> new RecordNotFoundException("Authority record was not found"));

        if (groupAuthorityDao.existsByAuthorityAndGroup(authority, group)) {
            throw new InvalidRequestException("Authority already assigned to the group");
        }

        val groupAuthority = new GroupAuthority();

        groupAuthority.setAuthority(authority);

        groupAuthority.setGroup(group);

        return groupAuthorityDao.save(groupAuthority);
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE_GROUP_AUTHORITY')")
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public GroupAuthority update(GroupAuthority updateDto) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public Collection<GroupAuthority> findByGroup(long groupId) {
        return groupAuthorityDao.findByGroup_Id(groupId);
    }

    @Override
    protected Class<GroupAuthority> getEntityClass() {
        return GroupAuthority.class;
    }

    public Page<GroupAuthority> findByGroup(long groupId, Pageable pageable) {
        return groupAuthorityDao.findByGroup_Id(groupId, pageable);
    }

    public Collection<GroupAuthority> createAuthorities(CreateGroupAuthorityCommand createGroupAuthorityCommand) {

        validate(createGroupAuthorityCommand);

        val group = groupDao.findById(createGroupAuthorityCommand.getGroupId())
                .orElseThrow(() -> new RecordNotFoundException("Group record was not found"));

        val groupAuthorities = new HashSet<GroupAuthority>();

        createGroupAuthorityCommand.getAuthorityIds().forEach(id -> {

            val authority = authorityDao.findById(createGroupAuthorityCommand.getAuthorityId())
                    .orElseThrow(() -> new RecordNotFoundException("Authority record was not found for id " + id));

            if (!groupAuthorityDao.existsByAuthorityAndGroup(authority, group)) {
                return;
            }

            val groupAuthority = new GroupAuthority();

            groupAuthority.setAuthority(authority);

            groupAuthority.setGroup(group);

            val persistedGroupAuthority = groupAuthorityDao.save(groupAuthority);

            groupAuthorities.add(persistedGroupAuthority);

        });

        return groupAuthorities;

    }

    public void delete(Collection<Long> groupAuthorityIds) {
        val authoritiesToBeDeleted = groupAuthorityDao.findAllById(groupAuthorityIds);
        groupAuthorityDao.deleteAll(authoritiesToBeDeleted);
    }
}
