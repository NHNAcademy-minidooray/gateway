package com.nhnacademy.minidooray.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String accountId;
    private String password;

    private String email;

    private String name;

    private LocalDate createdAt;

    private Integer statusCode;

    private Integer authorityCode;

}

