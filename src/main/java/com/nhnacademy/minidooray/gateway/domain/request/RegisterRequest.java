package com.nhnacademy.minidooray.gateway.domain.request;

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

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    private String accountId;

    @Length(min = 3, max = 20)
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @NotBlank
    @Email(message = "아이메일은 필수 입력값입니다.")
    private String email;

    private String name;

}
