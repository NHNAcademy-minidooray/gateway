package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

}
