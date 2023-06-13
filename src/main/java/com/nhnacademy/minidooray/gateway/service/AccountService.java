package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.UserModifyRequest;

import javax.servlet.http.HttpServletRequest;

public interface AccountService {

    Account createAccount(RegisterRequest request);
    Account getAccount(HttpServletRequest request);
    String getStatusName(Integer status);
    Account modifyForUser(HttpServletRequest httpServletRequest, UserModifyRequest request);
    void withdrawForUser(HttpServletRequest request);
    String getUserCookie(HttpServletRequest request, String value);
}
