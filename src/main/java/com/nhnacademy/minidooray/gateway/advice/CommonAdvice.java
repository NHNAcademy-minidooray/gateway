package com.nhnacademy.minidooray.gateway.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray.gateway.exception.ErrorMessage;
import com.nhnacademy.minidooray.gateway.exception.ValidationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler({ValidationFailedException.class, MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> missingParameter(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
