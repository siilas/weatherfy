package com.github.siilas.weatherfy.api.response;

import java.time.LocalDateTime;

import com.github.siilas.weatherfy.spotify.model.Genre;
import com.github.siilas.weatherfy.spotify.response.Tracks;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackSuggestion {

    private String city;
    private Double latitude;
    private Double longitude;
    private Double temperature;
    private LocalDateTime requestDate;
    private Genre genre;
    private Tracks tracks;

}
