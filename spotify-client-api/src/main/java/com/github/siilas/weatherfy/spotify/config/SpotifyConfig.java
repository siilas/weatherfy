package com.github.siilas.weatherfy.spotify.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spotify.app")
public class SpotifyConfig {

    private String api;
    private String auth;
    private String clientId;
    private String clientSecret;

    public String getAuthentication() {
        return clientId + ":" + clientSecret;
    }

}