package com.nhnacademy.minidooray.gateway.service;

import com.nhnacademy.minidooray.gateway.auth.AccountAdopter;
import com.nhnacademy.minidooray.gateway.domain.Account;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountAdopter accountAdopter;
    public CustomUserDetailsService(AccountAdopter accountAdopter) {
        this.accountAdopter = accountAdopter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountAdopter.getAccount(username);
        if(Objects.isNull(user)){
           throw new UsernameNotFoundException(username + " not found");
        }
        return new User(user.getAccountId(), user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority(String.valueOf(user.getAuthorityCode()))));
    }

}
