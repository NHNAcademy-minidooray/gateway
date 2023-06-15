package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.domain.TaskTitle;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProjectService {

    List<Project> getUserProjects(HttpServletRequest request);
    List<TaskTitle> getUserAllTasks(HttpServletRequest request);
    Project getProject(Integer projectId);

}
