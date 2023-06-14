package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Project;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProjectService {

    List<Project> getUserProjects(HttpServletRequest request);

}
