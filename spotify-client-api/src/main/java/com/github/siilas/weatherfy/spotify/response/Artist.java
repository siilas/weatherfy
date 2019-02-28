package com.github.siilas.weatherfy.spotify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Artist {

    private String name;
    
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

}
