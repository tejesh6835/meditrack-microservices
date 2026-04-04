package org.meditrack.authserver.auth_server.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class InternalTokenService {

    @Value("${app.issuer}")
    private String issuer;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getInternalAccessToken() {

        String tokenUrl = issuer + "/oauth2/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("auth-internal-client", "internal-secret");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials&scope=internal.write";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.exchange(tokenUrl, HttpMethod.POST, request, Map.class);

        return response.getBody().get("access_token").toString();
    }
}
