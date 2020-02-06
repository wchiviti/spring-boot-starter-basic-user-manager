package io.github.hobbstech.commons.usermanager.group.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.github.hobbstech.commons.usermanager.group.service.GroupService;
import io.github.hobbstech.commons.utilities.beans.BeanUtil;
import lombok.val;

import java.io.IOException;

import static java.util.Objects.isNull;

public class GroupDeserializer extends StdDeserializer<Group> {

    public GroupDeserializer() {
        super(Group.class);
    }

    @Override
    public Group deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        val groupId = jsonParser.readValueAs(Long.class);

        if (isNull(groupId)) {
            return null;
        }

        val groupService = BeanUtil.getBean(GroupService.class);

        return groupService.findById(groupId);
    }
}
