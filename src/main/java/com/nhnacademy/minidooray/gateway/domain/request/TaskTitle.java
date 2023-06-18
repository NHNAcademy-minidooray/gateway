package com.nhnacademy.minidooray.gateway.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

@Getter
@JsonRootName("task")
public class TaskTitle {
    @JsonProperty("id")
    private Integer taskSeq;
    @JsonProperty("title")
    private String taskTitle;
    @JsonProperty("project-id")
    private Integer projectSeq;
    @JsonProperty("project-title")
    private String projectTitle;

    public TaskTitle(Integer taskSeq, String taskTitle, Integer projectSeq, String projectTitle) {
        this.taskSeq = taskSeq;
        this.taskTitle = taskTitle;
        this.projectSeq = projectSeq;
        this.projectTitle = projectTitle;
    }
}
