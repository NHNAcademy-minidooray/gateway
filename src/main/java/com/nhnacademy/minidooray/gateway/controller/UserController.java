package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.UserModifyRequest;
import com.nhnacademy.minidooray.gateway.repository.AlreadyExistException;
import com.nhnacademy.minidooray.gateway.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final AccountService accountService;
    @GetMapping
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String joinForm(@Valid @ModelAttribute RegisterRequest request, Model model) throws AlreadyExistException {
        Account account = accountService.createAccount(request);
        model.addAttribute("account",account);
       return "join-success";
    }

    @GetMapping("/profile/view")
    public String viewProfile(Model model, HttpServletRequest request) {
        Account account = accountService.getAccount(request);
        String status = accountService.getStatusName(account.getStatusCode());
        model.addAttribute("account",account);
        model.addAttribute("status",status);
        return "/profile";
    }

    @PostMapping("/profile/modify")
    public String updateProfile(@Valid @ModelAttribute UserModifyRequest request, Model model, HttpServletRequest httpServletRequest) {
        Account account= accountService.modifyForUser(httpServletRequest,request);
        String status = accountService.getStatusName(account.getStatusCode());
        model.addAttribute("account",account);
        model.addAttribute("status",status);
        return "/profile";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model,HttpServletRequest request){
        model.addAttribute("account",accountService.getUserCookie(request,"accountName"));
        return "withdraw";
    }
    @GetMapping("/profile/withdraw")
    public String withdrawUser(HttpServletRequest request) {
        accountService.withdrawForUser(request);
        return "/login";
    }

}
