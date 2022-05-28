package com.tdc.outbox.user.command.newuser.response;

import com.tdc.outbox.commons.ObjectParser;
import com.tdc.outbox.user.User;

import org.springframework.stereotype.Component;

@Component
public class UserToUserCreatedDTOParser implements ObjectParser<User, UserCreatedResponseDTO> {

    @Override
    public UserCreatedResponseDTO parse(final User input) {
        final UserCreatedResponseDTO userCreatedResponseDTO = UserCreatedResponseDTO
                .builder()
                .id(input.getId())
                .name(input.getName())
                .email(input.getEmail())
                .build();

        return userCreatedResponseDTO;
    }
}