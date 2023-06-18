package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Task;
import com.nhnacademy.minidooray.gateway.domain.request.TaskRegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.request.TaskTitle;

import java.util.List;

public interface TaskService {
    List<TaskTitle> getProjectTasks(Integer projectId);
    List<TaskTitle> getUserAllTasks(String sessionId);
    Task getTask(Integer projectSeq, Integer taskSeq);
    Task createTask(TaskRegisterRequest registerRequest, Integer projectSeq, String sessionId);
}
