package ru.service.library.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserReaderRq {

    @NotNull(message = "Name must not be null")
    String name;
    @NotNull(message = "Surname must not be null")
    String sureName;

    String typeSubscribe;

    //@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$")
    @Email(message = "Email must be valid")
    String email;

}
