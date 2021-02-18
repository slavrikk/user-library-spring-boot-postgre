package ru.service.library.repo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserReader {

    public UserReader() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private String sureName;

    private String email;

    @OneToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn()
    private UserSubscribe subscribe;

}
