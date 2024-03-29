package com.nhnacademy.minidooray.gateway.configuration;

import com.nhnacademy.minidooray.gateway.adopter.AccountAdopter;
import com.nhnacademy.minidooray.gateway.auth.AuthFailureHandler;
import com.nhnacademy.minidooray.gateway.auth.LoginSuccessHandler;
import com.nhnacademy.minidooray.gateway.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http
                        .authorizeHttpRequests(a -> a
                                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                                .antMatchers("/").authenticated()
                                .antMatchers("/static/css/**").permitAll()
                                .antMatchers("/static/js/**").permitAll()
                                .anyRequest().permitAll())
                        .authorizeRequests(a -> a
                                .antMatchers("/login").anonymous()
                                .anyRequest().authenticated())
                        .formLogin(h -> h
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage("/login")
                                .loginProcessingUrl("/login-process")
                                .failureHandler(authFailureHandler())

                                .successHandler(loginSuccessHandler(null, null)))
                        .logout(h -> h
                                .deleteCookies("X-SESSION")
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/login"))
                        .csrf()
                            .disable()
                        .sessionManagement()
                            .sessionFixation()
                            .none()
                            .and()
                        .headers()
                        .cacheControl()
                        .and()
                        .defaultsDisabled()
                        .frameOptions().sameOrigin()
                        .and()
                        .oauth2Login()
                        .loginPage("/gitHub/login")
                        .clientRegistrationRepository(clientRegistrationRepository())
                        .successHandler(loginSuccessHandler(null, null))
                        .and()
                        .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        // UserDetailsService 와 PasswordEncoder 를 이용한 인증 처리 구현.
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(RedisTemplate<String, Object> redisTemplate, AccountAdopter accountAdopter) {
        return new LoginSuccessHandler(redisTemplate, accountAdopter);
    }

    @Bean
    public AuthFailureHandler authFailureHandler(){
        return new AuthFailureHandler();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(github());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    private ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .userNameAttributeName("name")
                .clientId("2c09b0f55dce7538cc6f")
                .clientSecret("2cbd7dbdf89221227291749b3656b0ad57051948")
                .build();
    }


}
