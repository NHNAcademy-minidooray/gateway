package com.nhnacademy.minidooray.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String id;
    private String password;

    private String email;

    private String name;

    private LocalDate createdAt;

    private int statueCode;

    private int authority;

}

