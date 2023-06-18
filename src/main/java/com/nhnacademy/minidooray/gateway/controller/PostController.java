package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.request.TaskTitle;
import com.nhnacademy.minidooray.gateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final TaskService taskService;
    @GetMapping
    public String getPostPage(@CookieValue(value = "X-SESSION",required = false)String sessionId, Model model){
        List<TaskTitle> tasks = taskService.getUserAllTasks(sessionId);
        model.addAttribute("tasks",tasks);
        return "post";
    }

}
