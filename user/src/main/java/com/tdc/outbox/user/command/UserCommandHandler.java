package com.tdc.outbox.user.command;

import com.tdc.outbox.commons.ObjectParser;
import com.tdc.outbox.event.DomainEvent;
import com.tdc.outbox.event.DomainEventService;
import com.tdc.outbox.event.EventType;
import com.tdc.outbox.user.User;
import com.tdc.outbox.user.command.newuser.NewUserCommand;
import com.tdc.outbox.user.command.newuser.payload.NewUserDTO;
import com.tdc.outbox.user.infraestructure.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCommandHandler {

    private final ObjectParser<NewUserDTO, User> newUserToUserParser;
    private final UserRepository userRepository;
    private final DomainEventService domainEventService;

    public UserCommandHandler(ObjectParser<NewUserDTO, User> newUserToUserParser, UserRepository userRepository, DomainEventService domainEventService) {
        this.newUserToUserParser = newUserToUserParser;
        this.userRepository = userRepository;
        this.domainEventService = domainEventService;
    }

    @Transactional
    public User handleNewUser(final NewUserCommand newUserCommand) {
        final User user = this.newUserToUserParser.parse(newUserCommand.getPayload());
        final User persistedUser = this.userRepository.save(user);
        final DomainEvent userCreatedEvent = DomainEvent
                .builder()
                .issuedAt(newUserCommand.getIssuedAt())
                .payload(persistedUser)
                .eventType(EventType.USER_CREATED)
                .build();
        this.domainEventService.save(userCreatedEvent);

        return persistedUser;
    }
}