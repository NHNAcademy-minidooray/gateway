package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.domain.request.ProjectRegisterRequest;

import java.util.List;

public interface ProjectService {

    List<Project> getUserProjects(String sessionId);
    Project getProject(Integer projectId);
    Integer createProject(ProjectRegisterRequest registerRequest, String sessionId);

}
