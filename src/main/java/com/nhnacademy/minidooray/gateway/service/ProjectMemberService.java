package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.MemberListDto;
import com.nhnacademy.minidooray.gateway.domain.ProjectMember;
import com.nhnacademy.minidooray.gateway.domain.request.ProjectMemberRegisterRequest;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMember> getProjectMembers(Integer projectId);
    List<MemberListDto> addProjectMember(ProjectMemberRegisterRequest registerRequest,
                                         Integer projectId, String sessionId);
}
