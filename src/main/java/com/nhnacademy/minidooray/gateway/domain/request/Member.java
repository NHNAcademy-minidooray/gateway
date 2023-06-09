package com.nhnacademy.minidooray.gateway.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @NotBlank(message = "id : 필수 입력값 입니다.")
    @JsonProperty("id")
    private String projectMemberId;
}
