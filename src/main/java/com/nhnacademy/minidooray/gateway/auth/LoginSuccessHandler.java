package com.nhnacademy.minidooray.gateway.auth;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final RedisTemplate<String,Object> redisTemplate;

    public LoginSuccessHandler(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
        throws IOException, ServletException {
        HttpSession session = request.getSession();

        Cookie cookie = new Cookie("LOGIN", session.getId());
        response.addCookie(cookie);
        
        // Redis 에 session 정보를 저장
        String username = authentication.getName();
        String authority = new ArrayList<>(authentication.getAuthorities()).get(0).getAuthority();

        redisTemplate.opsForHash().put(session.getId(), "username", username);
        redisTemplate.opsForHash().put(session.getId(), "authority", authority);

        super.onAuthenticationSuccess(request, response, authentication);

        response.sendRedirect("/");
    }

}
