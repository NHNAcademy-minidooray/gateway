package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.ProjectAdopter;
import com.nhnacademy.minidooray.gateway.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final AccountService accountService;
    private final ProjectAdopter projectAdopter;
    @Override
    public List<Project> getUserProjects(HttpServletRequest request) {
        String accountId = accountService.getUserCookie(request,"username");
        return projectAdopter.getUserProjects(accountId);
    }
}
