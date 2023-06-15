package com.nhnacademy.minidooray.gateway.interceptor;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Component
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {
    private final ProjectService projectService;
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<Project> projects =  projectService.getUserProjects(request);
        modelAndView.addObject("projects",projects);
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
