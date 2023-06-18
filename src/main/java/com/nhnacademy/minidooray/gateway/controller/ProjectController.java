package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.*;
import com.nhnacademy.minidooray.gateway.domain.request.ProjectRegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.request.TaskTitle;
import com.nhnacademy.minidooray.gateway.exception.ValidationFailedException;
import com.nhnacademy.minidooray.gateway.service.*;
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
public class ProjectController {
    private final TaskService taskService;
    private final ProjectService projectService;
    private final TagService tagService;
    private final ProjectMemberService projectMemberService;
    private final MilestoneService milestoneService;
    /***
     * 프로젝트 클릭 시 나오는 페이지
     * 프로젝트 상세 설명 및
     * 프롤젝트의 업무 리스트 출력
     */
    @GetMapping("/{projectId}")
    public String getProjectTasks(@PathVariable Integer projectId, Model model){
        List<TaskTitle> tasks = taskService.getProjectTasks(projectId);
        Project project = projectService.getProject(projectId);
        List<Tag> tags = tagService.getTags(projectId);
        List<ProjectMember> projectMembers = projectMemberService.getProjectMembers(projectId);
        List<Milestone> milestones = milestoneService.getMilestones(projectId);
        model.addAttribute("milestones",milestones);
        model.addAttribute("members",projectMembers);
        model.addAttribute("project",project);
        model.addAttribute("tasks",tasks);
        model.addAttribute("tags",tags);
        return "project_info";
    }
    @GetMapping("/create")
    public String getNewProjectPage(){
        return "create_project";
    }

    @PostMapping
    public String CreateProjectForm(@Valid @ModelAttribute ProjectRegisterRequest request, BindingResult bindingResult,
                                    @CookieValue(value = "X-SESSION", required = false) String sessionId){
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        projectService.createProject(request,sessionId);
        return "/post";
    }



}
