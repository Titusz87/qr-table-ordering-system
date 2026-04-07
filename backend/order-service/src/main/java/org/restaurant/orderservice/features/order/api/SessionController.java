package org.restaurant.orderservice.features.order.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {

    private final RestTemplate restTemplate;

    public SessionController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createSession(
            @RequestParam("tableId") Integer tableId // get tableId from query
    ) {

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("public_client", "public_secret");

        // Body for auth server
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("scope", "order.get order.post menu.get");

        HttpEntity<MultiValueMap<String, String>> authRequest = new HttpEntity<>(body, headers);

        // Call auth server
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "http://localhost:9000/oauth2/token",
                authRequest,
                Map.class
        );

        String accessToken = (String) response.getBody().get("access_token");

        // Response to frontend
        Map<String, String> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("tableId", tableId.toString());

        return ResponseEntity.ok(result);
    }
}