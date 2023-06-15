package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.domain.TaskTitle;
import com.nhnacademy.minidooray.gateway.service.ProjectService;
import com.nhnacademy.minidooray.gateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final TaskService taskService;
    private final ProjectService projectService;
    /***
     * 프로젝트 클릭 시 나오는 페이지
     * 프로젝트 상세 설명 및
     * 프롤젝트의 업무 리스트 출력
     */
    @GetMapping("/{projectId}")
    public String getProjectTasks(@PathVariable Integer projectId, HttpServletRequest request, Model model){
        List<TaskTitle> tasks = taskService.getProjectTasks(projectId);
        Project project = projectService.getProject(projectId);
        List<Project> projects =  projectService.getUserProjects(request);
        model.addAttribute("projects",projects);
        model.addAttribute("project",project);
        model.addAttribute("tasks",tasks);
        return "project-info";
    }
}
