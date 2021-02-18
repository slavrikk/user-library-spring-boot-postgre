package ru.service.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Integer id;

    private String name;

    private String sureName;

    private String email;

    private Integer subscribeId;

    private String subscriptionType;

    private List<BookDTO> books;
}
