package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.request.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.request.UserModifyRequest;

public interface AccountService {

    Account createAccount(RegisterRequest request);
    Account getAccount(String sessionId);
    String getStatusName(Integer status);
    Account modifyForUser(String sessionId, UserModifyRequest request);
    void withdrawForUser(String sessionId);
    String getUserCookie(String sessionId, String value);
}
