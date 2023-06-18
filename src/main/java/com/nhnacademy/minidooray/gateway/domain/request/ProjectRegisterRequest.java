package com.nhnacademy.minidooray.gateway.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProjectRegisterRequest {
    @NotBlank(message = "title : 필수 입력값 입니다.")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "content : 필수 입력값 입니다.")
    @JsonProperty("content")
    private String content;
}
