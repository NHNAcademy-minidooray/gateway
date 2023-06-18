package com.nhnacademy.minidooray.gateway.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.minidooray.gateway.domain.Account;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberRegisterRequest {
    @JsonProperty("members")
    private List<Member> projectMemberIds = new ArrayList<>();
}
