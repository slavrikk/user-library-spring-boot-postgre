package ru.service.library.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BookRq {

    @NotNull
    private String name;

    @NotNull
    private String author;

    @NotNull
    @Size(min = 4,max = 4)
    private Integer year;
}
