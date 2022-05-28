package com.tdc.outbox.user.command.newuser.payload;

import com.tdc.outbox.commons.ObjectParser;
import com.tdc.outbox.user.User;

import org.springframework.stereotype.Component;

@Component
public class NewUserDTOToUserParser implements ObjectParser<NewUserDTO, User> {

    @Override
    public User parse(final NewUserDTO input) {
        final User user = User
                .builder()
                .email(input.getEmail())
                .name(input.getName())
                .build();

        return user;
    }
}