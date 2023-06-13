package com.nhnacademy.minidooray.gateway.auth;

import com.nhnacademy.minidooray.gateway.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.FilterChain;
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

        // TODO #4-1: `SESSION` 이라는 이름의 쿠키에 sessionId를 저장하세요.
        Cookie cookie = new Cookie("SESSION",sessionId);
        response.addCookie(cookie);
        // TODO #4-2: redis에 session 정보를 저장하세요.
        String username = authentication.getName();
        String authority = new ArrayList<>(authentication.getAuthorities()).get(0).getAuthority();
        Account account = accountAdopter.getAccount(username);

        redisTemplate.opsForHash().put(sessionId, "username", username);
        redisTemplate.opsForHash().put(sessionId, "authority", authority);
        redisTemplate.opsForHash().put(sessionId,"accountName",account.getName());

//        Authentication authentication1 = new UsernamePasswordAuthenticationToken(
//
//        )

//        super.onAuthenticationSuccess(request, response, authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.sendRedirect("/post");
    }

}
