package com.nhnacademy.minidooray.gateway.auth;

import com.nhnacademy.minidooray.gateway.domain.Account;
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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("//accounts")
                .scheme("http").host(accountProperties.getHost()).port(accountProperties.getPort());

        if(Objects.nonNull(id)) {
            builder.queryParam("id",id);
        }
        URI uri = builder.build(false).encode().toUri();
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Account> exchange = restTemplate.exchange(uri, HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<Account>() {
                });
        return exchange.getBody();
    }

}
