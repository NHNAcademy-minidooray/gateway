package com.nhnacademy.minidooray.gateway.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray.gateway.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@ControllerAdvice
@RequiredArgsConstructor
public class CommonAdvice {
    private final ObjectMapper objectMapper;
    @ExceptionHandler(HttpClientErrorException.class)
    public String existsException(HttpClientErrorException e, Model model){
        String responseBodyAsString = e.getResponseBodyAsString();
        ExceptionDto dto;
        try {
             dto = objectMapper.readValue(responseBodyAsString, ExceptionDto.class);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        String error = dto.getMessage();
        model.addAttribute("errorMassage",error);
        return "/error";
    }
}
