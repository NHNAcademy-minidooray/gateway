package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.domain.Task;
import com.nhnacademy.minidooray.gateway.domain.request.TaskRegisterRequest;
import com.nhnacademy.minidooray.gateway.service.ProjectService;
import com.nhnacademy.minidooray.gateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;

    @GetMapping("/{projectId}/tasks/{taskId}")
    public String taskInfoPage(@PathVariable Integer projectId, @PathVariable Integer taskId,
                               Model model){
        Task task = taskService.getTask(projectId,taskId);
        Project project = projectService.getProject(projectId);
        model.addAttribute("task",task);
        model.addAttribute("project",project);
        return "/task";
    }

    @GetMapping("/tasks/create")
    public String getNewTaskPage(@ModelAttribute TaskRegisterRequest registerRequest, Integer projectSeq, String accountId){
        return "create_task";
    }



}
