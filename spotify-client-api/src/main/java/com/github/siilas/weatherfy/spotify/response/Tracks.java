package com.github.siilas.weatherfy.spotify.response;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tracks {

	@Singular("addTtrack")
    private List<Track> tracks;
    
    public List<Track> getTracks() {
    	if (tracks == null) {
    		tracks = Collections.emptyList();
    	}
    	return tracks;
    }
    
}
