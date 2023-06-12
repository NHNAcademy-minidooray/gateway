package com.nhnacademy.minidooray.gateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserModifyRequest {
    @Length(min = 3, max = 20)
    @NotBlank
    String password;

    @NotBlank
    String email;

    @NotBlank
    String name;
}
