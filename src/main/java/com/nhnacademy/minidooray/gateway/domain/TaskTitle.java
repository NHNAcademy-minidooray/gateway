package com.nhnacademy.minidooray.gateway.domain;

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

    public TaskTitle(Integer taskSeq, String taskTitle) {
        this.taskSeq = taskSeq;
        this.taskTitle = taskTitle;
    }
}
