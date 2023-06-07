package com.nhnacademy.minidooray.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    String accountId;

    @Length(min = 3, max = 20)
    @NotBlank
    String password;

    @NotBlank
    @Email
    String email;

    String name;

}
