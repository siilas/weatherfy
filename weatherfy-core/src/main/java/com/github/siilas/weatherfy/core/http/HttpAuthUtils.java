package com.github.siilas.weatherfy.core.http;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public final class HttpAuthUtils {

    private HttpAuthUtils() {
        throw new IllegalStateException();
    }

    public static HttpEntity<?> getAuth(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return new HttpEntity<String>(headers);
    }

    public static HttpEntity<?> getToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + Base64.encodeBase64String(token.getBytes()));
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        return new HttpEntity<MultiValueMap<String, String>>(body, headers);
    }

}
