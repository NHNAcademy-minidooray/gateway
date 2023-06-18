package com.nhnacademy.minidooray.gateway.advice;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class MyControllerAdvice {
    private final ProjectService projectService;

    @ModelAttribute
    public void preControllerProcess(@CookieValue(value = "X-SESSION", required = false) String sessionId, Model model){
        if (Objects.nonNull(sessionId)) {
            List<Project> projects =  projectService.getUserProjects(sessionId);
            model.addAttribute("projects",projects);
        }

    }
}
