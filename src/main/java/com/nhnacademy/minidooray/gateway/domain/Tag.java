package com.nhnacademy.minidooray.gateway.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
@Getter
@JsonRootName("tag")
public class Tag {
    @JsonProperty("id")
    private Integer tagSeq;

    @JsonProperty("name")
    private String tagName;

    @JsonProperty("project_id")
    private Integer projectSeq;

    public Tag(Integer tagSeq, String tagName, Integer projectSeq) {
        this.tagSeq = tagSeq;
        this.tagName = tagName;
        this.projectSeq = projectSeq;
    }
}
