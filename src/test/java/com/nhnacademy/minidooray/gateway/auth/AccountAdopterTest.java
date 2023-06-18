package com.nhnacademy.minidooray.gateway.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.minidooray.gateway.adopter.AccountAdopter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountAdopterTest {

    //    @MockBean
//    MockRestServiceServer mockServer;
    @Autowired
    PasswordEncoder passwordEncoder;
    @MockBean
    RestTemplate restTemplate;
    @InjectMocks
    AccountAdopter accountAdopter;

    @Test
    void getPassword() {
        String result = passwordEncoder.encode("ksy");
        System.out.println(result);
    }

    @Test
    void testCreateAccount() throws JsonProcessingException {


    }
}