package com.nhnacademy.minidooray.gateway.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonRootName("project")
@AllArgsConstructor
public class Project {
    @JsonProperty("id")
    private Integer projectSeq;
    @JsonProperty("title")
    private String projectTitle;
    @JsonProperty("content")
    private String projectContent;

    @JsonProperty("created-at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime projectCreatedAt;

    @JsonProperty("member")
    private String projectMemberId;

    @JsonProperty("role")
    private String projectMemberRole;
    @JsonProperty("status")
    private String statusCodeName;

}
