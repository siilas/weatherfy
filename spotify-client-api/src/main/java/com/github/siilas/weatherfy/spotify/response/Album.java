package com.github.siilas.weatherfy.spotify.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.siilas.weatherfy.spotify.deserializer.DateYearDeserializer;

import lombok.Data;

@Data
public class Album {

    private String name;
    
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    
    @JsonProperty("release_date")
    @JsonDeserialize(using = DateYearDeserializer.class)
    private Integer releaseDate;

    private List<Image> images;

}
