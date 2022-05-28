package com.tdc.outbox.user;

import com.tdc.outbox.commons.ObjectParser;
import com.tdc.outbox.user.command.UserCommandHandler;
import com.tdc.outbox.user.command.newuser.NewUserCommand;
import com.tdc.outbox.user.command.newuser.payload.NewUserDTO;
import com.tdc.outbox.user.command.newuser.response.UserCreatedResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    private final UserCommandHandler userCommandHandler;
    private final ObjectParser<User, UserCreatedResponseDTO> userToUserCreatedRDTOParser;

    public UserResource(UserCommandHandler userCommandHandler, ObjectParser<User, UserCreatedResponseDTO> userToUserCreatedRDTOParser) {
        this.userCommandHandler = userCommandHandler;
        this.userToUserCreatedRDTOParser = userToUserCreatedRDTOParser;
    }

    @PostMapping
    public ResponseEntity<UserCreatedResponseDTO> registerUser(@RequestBody final NewUserDTO newUserDTO) {
        final NewUserCommand newUserCommand = new NewUserCommand(newUserDTO);
        final User createdUser = this.userCommandHandler.handleNewUser(newUserCommand);
        final UserCreatedResponseDTO responseDTO = this.userToUserCreatedRDTOParser.parse(createdUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}