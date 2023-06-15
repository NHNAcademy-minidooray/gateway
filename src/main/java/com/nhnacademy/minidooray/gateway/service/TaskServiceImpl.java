package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.TaskAdopter;
import com.nhnacademy.minidooray.gateway.domain.TaskTitle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskAdopter taskAdopter;
    @Override
    public List<TaskTitle> getProjectTasks(Integer projectId){
        return taskAdopter.getProjectTasks(projectId);
    }
}
