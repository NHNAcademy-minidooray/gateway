package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.domain.TaskTitle;
import com.nhnacademy.minidooray.gateway.service.ProjectService;
import com.nhnacademy.minidooray.gateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final ProjectService projectService;
    private final TaskService taskService;
    @GetMapping
    public String getPostPage(HttpServletRequest request, Model model){
        List<Project> projects =  projectService.getUserProjects(request);
        List<TaskTitle> tasks = taskService.getUserAllTasks(request);
        model.addAttribute("projects",projects);
        model.addAttribute("tasks",tasks);
        return "post";
    }

}
