package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.TaskAdopter;
import com.nhnacademy.minidooray.gateway.domain.Task;
import com.nhnacademy.minidooray.gateway.domain.request.TaskRegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.request.TaskTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskAdopter taskAdopter;
    private final AccountService accountService;
    @Override
    public List<TaskTitle> getProjectTasks(Integer projectId){
        return taskAdopter.getProjectTasks(projectId);
    }

    @Override
    public List<TaskTitle> getUserAllTasks(String sessionId) {
        String accountId = accountService.getUserCookie(sessionId,"username");
        return taskAdopter.getUserAllTasks(accountId);
    }
    @Override
    public Task getTask(Integer projectSeq, Integer taskSeq){
       return taskAdopter.getTask(projectSeq,taskSeq);
    }
    public Task createTask(TaskRegisterRequest registerRequest, Integer projectSeq, String accountId){
        return taskAdopter.createTask(registerRequest,projectSeq,accountId);
    }
}
