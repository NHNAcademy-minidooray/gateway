package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.Milestone;
import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.domain.Tag;
import com.nhnacademy.minidooray.gateway.domain.Task;
import com.nhnacademy.minidooray.gateway.domain.request.TaskRegisterRequest;
import com.nhnacademy.minidooray.gateway.exception.ValidationFailedException;
import com.nhnacademy.minidooray.gateway.service.MilestoneService;
import com.nhnacademy.minidooray.gateway.service.ProjectService;
import com.nhnacademy.minidooray.gateway.service.TagService;
import com.nhnacademy.minidooray.gateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final TagService tagService;
    private final MilestoneService milestoneService;

    @GetMapping("/{projectId}/tasks/{taskId}")
    public String taskInfoPage(@PathVariable Integer projectId, @PathVariable Integer taskId,
                               Model model){
        Task task = taskService.getTask(projectId,taskId);
        Project project = projectService.getProject(projectId);
        model.addAttribute("task",task);
        model.addAttribute("project",project);
        return "/task";
    }

    @GetMapping("/tasks/create/{projectId}")
    public String getNewTaskPage(@PathVariable Integer projectId, Model model){
        List<Tag> tags = tagService.getTags(projectId);
        List<Milestone> milestones = milestoneService.getMilestones(projectId);
        model.addAttribute("projectId",projectId);
        model.addAttribute("milestones",milestones);
        model.addAttribute("tags",tags);
        return "create_task";

    }

    @PostMapping("/tasks/create/{projectId}")
    public String createTaskForm(@Valid @ModelAttribute TaskRegisterRequest registerRequest,
                                 BindingResult bindingResult,@PathVariable Integer projectId, Model model,
                                 @CookieValue(value = "X-SESSION", required = false) String sessionId){
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Task task = taskService.createTask(registerRequest,projectId,sessionId);
        Project project = projectService.getProject(projectId);
        model.addAttribute("task",task);
        model.addAttribute("project",project);
        return "redirect:/projects/"+project.getProjectSeq()+"/tasks/"+task.getTaskSeq();
    }




}
