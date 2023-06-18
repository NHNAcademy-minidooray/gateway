package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.request.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.request.UserModifyRequest;
import com.nhnacademy.minidooray.gateway.exception.ValidationFailedException;
import com.nhnacademy.minidooray.gateway.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final AccountService accountService;
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String joinForm(@Valid @ModelAttribute RegisterRequest request, BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Account account = accountService.createAccount(request);
        model.addAttribute("account",account);
       return "join_success";
    }

    @GetMapping("/profile/view")
    public String viewProfile(Model model,@CookieValue(value = "X-SESSION",required = false) String sessionId) {
        Account account = accountService.getAccount(sessionId);
        String status = accountService.getStatusName(account.getStatusCode());
        model.addAttribute("account",account);
        model.addAttribute("status",status);
        return "/profile";
    }

    @PostMapping("/profile/modify")
    public String updateProfile(@Valid @ModelAttribute UserModifyRequest request,BindingResult bindingResult,
                                Model model, @CookieValue(value = "X-SESSION",required = false) String sessionId) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Account account= accountService.modifyForUser(sessionId,request);
        String status = accountService.getStatusName(account.getStatusCode());
        model.addAttribute("account",account);
        model.addAttribute("status",status);
        return "/profile";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model,@CookieValue(value = "X-SESSION", required = false) String sessionId){
        model.addAttribute("account",accountService.getUserCookie(sessionId,"accountName"));
        return "withdraw";
    }
    @GetMapping("/profile/withdraw")
    public String withdrawUser(@CookieValue(value = "X-SESSION",required = false) String sessionId) {
        accountService.withdrawForUser(sessionId);
        return "/login";
    }

}
