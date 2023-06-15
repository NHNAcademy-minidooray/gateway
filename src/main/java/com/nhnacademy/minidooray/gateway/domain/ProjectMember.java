package com.nhnacademy.minidooray.gateway.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProjectMember {
    @JsonProperty("id")
    private Integer projectMemberSeq;

    @JsonProperty("username")
    private String projectMemberId;

    @JsonProperty("auth")
    private String projectMemberRole;

    public ProjectMember(Integer projectMemberSeq, String projectMemberId, String projectMemberRole) {
        this.projectMemberSeq = projectMemberSeq;
        this.projectMemberId = projectMemberId;
        this.projectMemberRole = projectMemberRole;
    }
}
