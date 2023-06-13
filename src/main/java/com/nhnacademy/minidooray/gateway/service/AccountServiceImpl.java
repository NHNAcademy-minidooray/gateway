package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.auth.AccountAdopter;
import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.UserModifyRequest;
import com.nhnacademy.minidooray.gateway.repository.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountAdopter accountAdopter;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String,Object> redisTemplate;


    @Override
    public Account createAccount(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
       return accountAdopter.createAccount(request);
    }

    @Override
    public Account getAccount(HttpServletRequest request) {
        String accountId = getUserCookie(request,"username");
        return accountAdopter.getAccount(accountId);
    }

    @Override
    public Account modifyForUser(HttpServletRequest httpServletRequest, UserModifyRequest request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String accountId = getUserCookie(httpServletRequest,"username");
        return accountAdopter.modifyForUser(accountId,request);
    }

    @Override
    public void withdrawForUser(HttpServletRequest request){
        String userId = getUserCookie(request,"username");
        accountAdopter.withdrawForUser(request,userId);
    }

    @Override
    public String getStatusName(Integer status){
        if(status == 1){
            return "사용 중";
        } else if (status==2) {
            return "탈퇴";
        }
        return "휴면";
    }

    @Override
    public String getUserCookie(HttpServletRequest request, String value) {
        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("SESSION")) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        if(value.equals("username")){
            return  (String) redisTemplate.opsForHash().get(sessionId,"username");
        } else if (value.equals("accountName")) {
            return  (String) redisTemplate.opsForHash().get(sessionId,"accountName");
        } else {
            return  (String) redisTemplate.opsForHash().get(sessionId,"authority");
        }
    }




}
