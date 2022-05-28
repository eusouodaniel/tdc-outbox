package com.tdc.outbox.user.command.newuser;

import com.tdc.outbox.command.AbstractCommand;
import com.tdc.outbox.user.command.newuser.payload.NewUserDTO;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class NewUserCommand extends AbstractCommand {

    public NewUserCommand(NewUserDTO newUserDTO) {
        super(UUID.randomUUID().toString(), LocalDateTime.now(), newUserDTO);
    }
}