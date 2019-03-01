package com.github.siilas.weatherfy.openweathermap.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "openweathermap.app")
public class OpenWeatherMapConfig {

    private String id;
    private String host;
    private String port;
    private String scheme;
    
}
