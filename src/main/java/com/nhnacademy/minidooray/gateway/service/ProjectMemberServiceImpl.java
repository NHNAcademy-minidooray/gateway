package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.AccountAdopter;
import com.nhnacademy.minidooray.gateway.adopter.ProjectMemberAdopter;
import com.nhnacademy.minidooray.gateway.domain.MemberListDto;
import com.nhnacademy.minidooray.gateway.domain.ProjectMember;
import com.nhnacademy.minidooray.gateway.domain.request.ProjectMemberRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberAdopter projectMemberAdopter;
    private final AccountService accountService;
    @Override
    public List<ProjectMember> getProjectMembers(Integer projectId) {
        return projectMemberAdopter.getProjectMembers(projectId);
    }
    @Override
    public List<MemberListDto> addProjectMember(ProjectMemberRegisterRequest registerRequest,
                                                Integer projectId, String sessionId) {
       String accountId = accountService.getUserCookie(sessionId,"username");
       return projectMemberAdopter.addProjectMember(registerRequest,projectId,accountId);
    }
}
