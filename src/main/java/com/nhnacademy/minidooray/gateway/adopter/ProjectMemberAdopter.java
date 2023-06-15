package com.nhnacademy.minidooray.gateway.adopter;

import com.nhnacademy.minidooray.gateway.domain.ProjectMember;
import com.nhnacademy.minidooray.gateway.properties.GatewayProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProjectMemberAdopter {
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;
    private static final HttpEntity<String> REQUEST_ENTITY = new HttpEntity<>(getHttpHeader());

    /**
     * 프로젝트별 멤버 조회
     * @param projectId
     * @return
     */
    public List<ProjectMember> getProjectMembers(Integer projectId) {
       URI uri = getUri(String.valueOf(projectId),"/projects/{projectId}/accounts");
        ResponseEntity<List<ProjectMember>> response = restTemplate.exchange(uri, HttpMethod.GET,
                REQUEST_ENTITY, new ParameterizedTypeReference<>() {});
        return response.getBody();
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
