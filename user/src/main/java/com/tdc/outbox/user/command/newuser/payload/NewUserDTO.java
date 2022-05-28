package com.tdc.outbox.user.command.newuser.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class NewUserDTO implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;
}