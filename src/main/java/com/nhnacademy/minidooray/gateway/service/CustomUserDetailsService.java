package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.auth.AccountAdopter;
import com.nhnacademy.minidooray.gateway.domain.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountAdopter accountAdopter;

    public CustomUserDetailsService(AccountAdopter accountAdopter) {
        this.accountAdopter = accountAdopter;
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account user = accountAdopter.getAccount(userId);
        if(Objects.isNull(user)){

        }
        return new User(user.getId(), user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
