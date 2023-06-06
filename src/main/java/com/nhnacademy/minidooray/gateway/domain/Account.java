package com.nhnacademy.minidooray.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Account {
    private String id;
    private String password;

    private String email;

    private String name;

    private Date createdAt;

    private String statueCode;

}

