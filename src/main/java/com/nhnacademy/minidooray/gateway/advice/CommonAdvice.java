package com.nhnacademy.minidooray.gateway.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CommonAdvice {
    @ExceptionHandler(HttpClientErrorException.class)
    public String existsException(HttpClientErrorException e, Model model){
//        model.addAttribute()
        return "/join";
    }
}
