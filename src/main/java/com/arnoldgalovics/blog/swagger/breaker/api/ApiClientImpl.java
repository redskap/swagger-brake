package com.arnoldgalovics.blog.swagger.breaker.api;

import java.util.Collection;
import java.util.Collections;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiClientImpl implements ApiClient {
    private final JsonConverter jsonConverter;

    private RestTemplate restTemplate = new RestTemplate();

    public void upload(Collection<BreakingChange> breakingChanges, String apiServer, String project) {
        String uri = apiServer + "/breaking-changes";
        for (BreakingChange bc : breakingChanges) {
            upload(bc, uri, project);
        }
    }

    private void upload(BreakingChange breakingChange, String uri, String project) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        AddBreakingChangeRequest request = new AddBreakingChangeRequest(breakingChange.getMessage(), project);
        try {
            HttpEntity<String> entity = new HttpEntity<>(jsonConverter.convert(request), headers);
            restTemplate.postForEntity(uri, entity, Void.class, Collections.emptyMap());
        } catch (Exception e) {
            throw new UploadException("Cannot upload data to the provided server", e);
        }
    }
}
