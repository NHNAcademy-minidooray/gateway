package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.auth.AccountAdopter;
import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.UserModifyRequest;
import com.nhnacademy.minidooray.gateway.repository.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final AccountAdopter accountAdopter;
    private final PasswordEncoder passwordEncoder;
    @GetMapping
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String joinForm(@Valid @ModelAttribute RegisterRequest request, Model model) throws AlreadyExistException {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Account account = accountAdopter.createAccount(request);
        model.addAttribute("account",account);
       return "join-success";
    }

    @GetMapping("/profile/view")
    public String viewProfile(Model model, HttpServletRequest request) {
        String accountId = accountAdopter.getUserCookie(request,"username");
        Account account = accountAdopter.getAccount(accountId);
        String status = accountAdopter.getStatusName(account.getStatusCode());
        model.addAttribute("account",account);
        model.addAttribute("status",status);
        return "/profile";
    }

    @PostMapping("/profile/modify")
    public String updateProfile(@Valid @ModelAttribute UserModifyRequest request, Model model, HttpServletRequest httpServletRequest) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String accountId = accountAdopter.getUserCookie(httpServletRequest,"username");
        Account account= accountAdopter.modifyForUser(accountId,request);
        String status = accountAdopter.getStatusName(account.getStatusCode());
        model.addAttribute("account",account);
        model.addAttribute("status",status);
        return "/profile";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model,HttpServletRequest request){
        model.addAttribute("account",accountAdopter.getUserCookie(request,"accountName"));
        return "withdraw";
    }
    @GetMapping("/profile/withdraw")
    public String withdrawUser(HttpServletRequest request) {
        accountAdopter.withdrawForUser(request);
        return "/login";
    }

}
