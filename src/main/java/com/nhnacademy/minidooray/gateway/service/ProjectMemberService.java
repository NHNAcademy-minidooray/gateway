package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.ProjectMember;

import java.util.List;

public interface ProjectMemberService {
    List<ProjectMember> getProjectMembers(Integer projectId);
}
