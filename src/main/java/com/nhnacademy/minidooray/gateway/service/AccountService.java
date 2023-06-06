package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account createAccount(Account accountDTO);
    Account getAccount(Long id);

    String deleteAccount(Long id);
}
