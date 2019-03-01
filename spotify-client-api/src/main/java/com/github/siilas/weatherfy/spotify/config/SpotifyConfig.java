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

    private String apiHost;
    private String apiPort;
    private String authHost;
    private String authPort;
    private String scheme;
    private String clientId;
    private String clientSecret;

    public byte[] getAuthentication() {
        return (clientId + ":" + clientSecret).getBytes();
    }

}
