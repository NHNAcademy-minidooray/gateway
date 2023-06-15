package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.*;
import com.nhnacademy.minidooray.gateway.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String getProjectTasks(@PathVariable Integer projectId, HttpServletRequest request, Model model){
        List<TaskTitle> tasks = taskService.getProjectTasks(projectId);
        Project project = projectService.getProject(projectId);
        List<Project> projects =  projectService.getUserProjects(request);
        List<Tag> tags = tagService.getTags(projectId);
        List<ProjectMember> projectMembers = projectMemberService.getProjectMembers(projectId);
        List<Milestone> milestones = milestoneService.getMilestones(projectId);
        model.addAttribute("milestones",milestones);
        model.addAttribute("members",projectMembers);
        model.addAttribute("projects",projects);
        model.addAttribute("project",project);
        model.addAttribute("tasks",tasks);
        model.addAttribute("tags",tags);
        return "project-info";
    }
    @GetMapping("/create")
    public String getNewProjectPage(){
        return "create_project";
    }

    @PostMapping
    public String CreateProjectForm(){

        return "create_project";
    }

}
