package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.adopter.AccountAdopter;
import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.request.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.request.UserModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Account getAccount(String sessionId) {
        String accountId = getUserCookie(sessionId,"username");
        return accountAdopter.getAccount(accountId);
    }

    @Override
    public Account modifyForUser(String sessionId, UserModifyRequest request){
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        String accountId = getUserCookie(sessionId,"username");
        return accountAdopter.modifyForUser(accountId,request);
    }

    @Override
    public void withdrawForUser(String sessionId){
        String userId = getUserCookie(sessionId,"username");
        accountAdopter.withdrawForUser(userId);
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
    public String getUserCookie(String sessionId, String value) {
        if(value.equals("username")){
            return  (String) redisTemplate.opsForHash().get(sessionId,"username");
        } else if (value.equals("accountName")) {
            return  (String) redisTemplate.opsForHash().get(sessionId,"accountName");
        } else {
            return  (String) redisTemplate.opsForHash().get(sessionId,"authority");
        }
    }




}
