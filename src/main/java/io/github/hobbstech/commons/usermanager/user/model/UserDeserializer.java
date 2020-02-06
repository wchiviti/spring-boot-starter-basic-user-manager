package io.github.hobbstech.commons.usermanager.user.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.github.hobbstech.commons.usermanager.user.service.UserReaderService;
import io.github.hobbstech.commons.utilities.beans.BeanUtil;
import lombok.val;

import java.io.IOException;

import static java.util.Objects.isNull;

public class UserDeserializer extends StdDeserializer<User> {

    protected UserDeserializer() {
        super(User.class);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        val userId = jsonParser.readValueAs(Long.class);

        if(isNull(userId)){
            return null;
        }

        val userService = BeanUtil.getBean(UserReaderService.class);

        return userService.findById(userId);
    }
}
