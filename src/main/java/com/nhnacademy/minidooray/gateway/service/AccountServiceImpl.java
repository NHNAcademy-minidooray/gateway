package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final RestTemplate restTemplate;

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Account createAccount(Account accountDTO) {
        return null;
    }

    @Override
    public Account getAccount(Long id) {
        return null;
    }

    @Override
    public String deleteAccount(Long id) {
        return null;
    }
}
