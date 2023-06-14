package com.nhnacademy.minidooray.gateway.adopter;

import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.UserModifyRequest;
import com.nhnacademy.minidooray.gateway.properties.GatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Objects;


@Component
@Slf4j
public class AccountAdopter {

    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    private final PasswordEncoder passwordEncoder;

    public AccountAdopter(RestTemplate restTemplate, GatewayProperties gatewayProperties, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.gatewayProperties = gatewayProperties;
        this.passwordEncoder = passwordEncoder;
    }

    public Account getAccount(String id) {
        HttpEntity<String> requestEntity = new HttpEntity<>(getHttpHeader());
        URI uri = getUri(id, "/accountapi/accounts/{id}");
        log.info("&&&&&&&&&&&&&& uri : {}" , uri);

        ResponseEntity<Account> exchange = restTemplate.exchange(uri,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    public Account createAccount(RegisterRequest registerRequest) {
        HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(registerRequest, getHttpHeader());
        URI uri = getUri(null, "/accountapi/accounts");

        ResponseEntity<Account> exchange = restTemplate.exchange(uri,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    public Account modifyForUser(String id, UserModifyRequest request) {
        String newPwd = passwordEncoder.encode(request.getPassword());
        request.setPassword(newPwd);
        URI uri = getUri(id, "/accountapi/accounts/{id}");
        return restTemplate.patchForObject(uri, request, Account.class);
    }

    public void withdrawForUser(HttpServletRequest request, String userId){
        URI uri = getUri(userId, "/accountapi/accounts/withdraw/{id}");
        restTemplate.getForEntity(uri,Account.class);
    }

//    public List<Account> getAccountsForAdmin() {
//        URI uri = getUri(null,"/admin/accounts")
//        restTemplate.getForEntity()
//    }

    public HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    public URI getUri(String param, String path) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path)
                .scheme("http").host(gatewayProperties.getHost()).port(gatewayProperties.getPort());

        if (Objects.nonNull(param)) {
            return builder.build(param);
        } else {
            return builder.build(false).encode().toUri();
        }
    }

}
