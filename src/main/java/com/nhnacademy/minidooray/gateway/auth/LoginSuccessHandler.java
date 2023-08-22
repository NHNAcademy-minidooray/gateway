package com.nhnacademy.minidooray.gateway.auth;

import com.nhnacademy.minidooray.gateway.adopter.AccountAdopter;
import com.nhnacademy.minidooray.gateway.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final RedisTemplate<String,Object> redisTemplate;
    private final AccountAdopter accountAdopter;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
        throws IOException, ServletException {

        String sessionId = UUID.randomUUID().toString();
        // `SESSION`쿠키에 sessionId를 저장
        Cookie cookie = new Cookie("X-SESSION",sessionId);
        response.addCookie(cookie);
        // redis에 session 정보를 저장
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String authority = new ArrayList<>(userDetails.getAuthorities()).get(0).getAuthority();
        Account account = accountAdopter.getAccount(username);

        redisTemplate.opsForHash().put(sessionId, "username", username);
        redisTemplate.opsForHash().put(sessionId, "authority", authority);
        redisTemplate.opsForHash().put(sessionId,"accountName",account.getName());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.sendRedirect("/post");
    }

}
