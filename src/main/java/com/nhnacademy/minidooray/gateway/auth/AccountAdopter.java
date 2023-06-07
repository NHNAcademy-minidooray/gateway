package com.nhnacademy.minidooray.gateway.auth;

import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import com.nhnacademy.minidooray.gateway.properties.AccountProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Component
public class AccountAdopter {

    private final RestTemplate restTemplate;
    private final AccountProperties accountProperties;

    public AccountAdopter(RestTemplate restTemplate, AccountProperties accountProperties) {
        this.restTemplate = restTemplate;
        this.accountProperties = accountProperties;
    }

    public Account getAccount(String id) {
        HttpEntity<String> requestEntity = new HttpEntity<>(getHttpHeader());
        URI uri = getUri(id, "/account");
        ResponseEntity<Account> exchange = restTemplate.exchange(uri,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    public Account createAccount(RegisterRequest registerRequest) {
        HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(registerRequest,getHttpHeader());
        URI uri = getUri(null, "/accounts");
        ResponseEntity<Account> exchange = restTemplate.exchange(uri,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    public HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    public URI getUri(String param, String path) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path)
                .scheme("http").host(accountProperties.getHost()).port(accountProperties.getPort());

        if (Objects.nonNull(param)) {
            builder.queryParam("id", param);
        }
        return builder.build(false).encode().toUri();
    }
}
