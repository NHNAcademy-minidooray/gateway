package com.nhnacademy.minidooray.gateway.adopter;

import com.nhnacademy.minidooray.gateway.domain.Task;
import com.nhnacademy.minidooray.gateway.domain.request.TaskRegisterRequest;
import com.nhnacademy.minidooray.gateway.domain.request.TaskTitle;
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
public class TaskAdopter {
    private final RestTemplate restTemplate;
    private final GatewayProperties gatewayProperties;

    private static final HttpEntity<String> REQUEST_ENTITY = new HttpEntity<>(getHttpHeader());

    /**
     * 해당 유저의 모든 업무 조회
     * @param accountId
     * @return
     */
    public List<TaskTitle> getUserAllTasks(String accountId){
        URI uri = getUri(accountId,"/projects/tasks/{accountId}");
        ResponseEntity<List<TaskTitle>> response = restTemplate.exchange(uri, HttpMethod.GET,
                REQUEST_ENTITY, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
    public List<TaskTitle> getProjectTasks(Integer projectId){
        URI uri = getUri(String.valueOf(projectId),"/projects/{projectId}/tasks");
        ResponseEntity<List<TaskTitle>> response = restTemplate.exchange(uri,HttpMethod.GET,
                REQUEST_ENTITY, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    /**
     * 해당 업무 조회 하기
     * @return
     */
    public Task getTask(Integer projectSeq, Integer taskSeq){
        URI uri = getUri(projectSeq, String.valueOf(taskSeq),"/projects/{projectId}/tasks/{taskId}");
        return restTemplate.getForEntity(uri,Task.class).getBody();
    }

    /**
     * 업무 등록
     * @return
     */
    public Task createTask(TaskRegisterRequest registerRequest, Integer projectSeq, String accountId){
        URI uri = getUri(projectSeq,accountId,"/projects/{projectId}/tasks/accounts/{accountId}");
        return restTemplate.postForEntity(uri,registerRequest,Task.class).getBody();
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
    public URI getUri(Integer param1,String param2, String path) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path)
                .scheme("http").host(gatewayProperties.getHost()).port(gatewayProperties.getPort());

        if (Objects.nonNull(param1) && Objects.nonNull(param2)) {
            return builder.build().expand(param1,param2).toUri();
        } else {
            return builder.build(false).encode().toUri();
        }
    }

}
