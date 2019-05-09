package com.github.siilas.weatherfy.spotify.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.siilas.weatherfy.spotify.deserializer.DateYearDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private String name;
    
    @JsonProperty("release_date")
    @JsonDeserialize(using = DateYearDeserializer.class)
    private Integer releaseDate;

}
