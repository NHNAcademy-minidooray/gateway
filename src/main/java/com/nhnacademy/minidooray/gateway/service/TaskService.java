package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.TaskTitle;

import java.util.List;

public interface TaskService {
    List<TaskTitle> getProjectTasks(Integer projectId);
}
