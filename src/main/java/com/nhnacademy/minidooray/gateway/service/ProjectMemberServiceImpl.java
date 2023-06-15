package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.ProjectMemberAdopter;
import com.nhnacademy.minidooray.gateway.domain.ProjectMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberAdopter projectMemberAdopter;
    @Override
    public List<ProjectMember> getProjectMembers(Integer projectId) {
        return projectMemberAdopter.getProjectMembers(projectId);
    }
}
