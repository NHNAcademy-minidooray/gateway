package com.nhnacademy.minidooray.gateway.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray.gateway.domain.Account;
import com.nhnacademy.minidooray.gateway.domain.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountAdopterTest {

    @MockBean
    MockRestServiceServer mockServer;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AccountAdopter accountAdopter;

    @BeforeEach
    public void setup() {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void getAccount() {
    }

    @Test
    void testCreateAccount() throws JsonProcessingException {
        //Mock response
        Account account = new Account("moon","1234","email","문은지", LocalDate.now(),1,1);
        ObjectMapper objectMapper = new ObjectMapper();
        RegisterRequest registerRequest = new RegisterRequest("moon","1234","email","문은지");
        mockServer.expect(requestTo("/accounts"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().json(objectMapper.writeValueAsString(registerRequest)))
                .andRespond(withSuccess(objectMapper.writeValueAsString(account), MediaType.APPLICATION_JSON));

        //create account
        RegisterRequest registerRequest2 = new RegisterRequest("moon","1234","email","문은지");
        HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(registerRequest2, accountAdopter.getHttpHeader());
        URI uri = accountAdopter.getUri(null, "/accounts");
        ResponseEntity<Account> exchange = restTemplate.exchange(uri,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {});
        Account createdAccount = exchange.getBody();

        // Assertion
        assertNotNull(createdAccount);
        assertEquals("moon", createdAccount.getId());

        // Verify the mock server
        mockServer.verify();


    }
}