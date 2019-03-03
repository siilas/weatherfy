package com.github.siilas.weatherfy.spotify.response;

import java.util.Collections;
import java.util.List;

import lombok.Data;

@Data
public class Tracks {

    private List<Track> tracks;
    
    public List<Track> getTracks() {
    	if (tracks == null) {
    		tracks = Collections.emptyList();
    	}
    	return tracks;
    }
    
}
