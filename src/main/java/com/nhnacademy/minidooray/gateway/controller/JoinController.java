package com.nhnacademy.minidooray.gateway.controller;

import com.nhnacademy.minidooray.gateway.auth.AccountAdopter;
import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/join")
public class JoinController {
    private AccountAdopter accountAdopter;
    private BCryptPasswordEncoder bCrypt;


    @PostMapping("/submit")
    public String signup(@Valid @RequestBody RegisterRequest request){
        request.setPassword(bCrypt.encode(request.getPassword()));
        accountAdopter.createAccount(request);



//        accountAdopter.
       return "login";
    }

}
