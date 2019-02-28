package com.github.siilas.weatherfy.spotify.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Track {

    private String name;

    private Album album;

    private List<Artist> artists;

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

}
