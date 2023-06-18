package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.ProjectAdopter;
import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.domain.request.ProjectRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final AccountService accountService;
    private final ProjectAdopter projectAdopter;
    @Override
    public List<Project> getUserProjects(String sessionId) {
        String accountId = accountService.getUserCookie(sessionId,"username");
        return projectAdopter.getUserProjects(accountId);
    }
    @Override
    public Project getProject(Integer projectId) {
        return projectAdopter.getProject(projectId);
    }

    @Override
    public Integer createProject(ProjectRegisterRequest registerRequest, String sessionId){
        String accountId = accountService.getUserCookie(sessionId,"username");
        Project project = projectAdopter.createProject(registerRequest,accountId);
        return project.getProjectSeq();
    }

}
