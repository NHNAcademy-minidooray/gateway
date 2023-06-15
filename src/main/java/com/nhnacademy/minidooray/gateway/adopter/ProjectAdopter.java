package com.nhnacademy.minidooray.gateway.adopter;

import com.nhnacademy.minidooray.gateway.domain.Project;
import com.nhnacademy.minidooray.gateway.properties.GatewayProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectAdopter {
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    private static final  HttpEntity<String> REQUEST_ENTITY = new HttpEntity<>(getHttpHeader());

    public List<Project> getUserProjects(String accountId){
        URI uri = getUri(accountId,"/projects/accounts/{accountId}");
        ResponseEntity<List<Project>> response = restTemplate.exchange(uri, HttpMethod.GET,
                REQUEST_ENTITY, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    /**
     * 프로젝트 상세 정보 조회
     * @return
     */
    public Project getProject(Integer projectId){
        URI uri = getUri(String.valueOf(projectId),"/projects/{projectId}");
       return restTemplate.getForEntity(uri,Project.class).getBody();
    }

    static public HttpHeaders getHttpHeader() {
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
