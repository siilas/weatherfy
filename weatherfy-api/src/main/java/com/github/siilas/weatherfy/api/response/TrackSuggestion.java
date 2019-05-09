package com.github.siilas.weatherfy.api.response;

import java.time.LocalDateTime;
import java.util.List;

import com.github.siilas.weatherfy.openweathermap.response.City;
import com.github.siilas.weatherfy.spotify.model.Genre;
import com.github.siilas.weatherfy.spotify.response.Track;
import com.github.siilas.weatherfy.spotify.response.Tracks;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TrackSuggestion {

    private String city;
    private Double latitude;
    private Double longitude;
    private Double temperature;
    private LocalDateTime requestDate;
    private Genre genre;
    private List<Track> tracks;
    private String message;
    
    public static class Builder {

    	private City city;
    	private Genre genre;
    	private Tracks tracks;
    	
    	public Builder city(City city) {
    		this.city = city;
    		return this;
    	}
    	
    	public Builder genre(Genre genre) {
    		this.genre = genre;
    		return this;
    	}
    	
    	public Builder tracks(Tracks tracks) {
    		this.tracks = tracks;
    		return this;
    	}
    	
    	public TrackSuggestion build() {
    		TrackSuggestion response = new TrackSuggestion();
    		response.setCity(city.getName());
    		response.setLatitude(city.getCoordinates().getLatitude());
    		response.setLongitude(city.getCoordinates().getLongitude());
    		response.setRequestDate(city.getDate());
    		response.setTemperature(city.getTemperature().getValue());
    		response.setGenre(genre);
    		response.setTracks(tracks.getTracks());
    		return response;
    	}
    	
    }
    
    @Data
    @NoArgsConstructor
    public static class TracksWrapper {
    	
    	private City city;
    	private Genre genre;
    	private Tracks tracks;
    	
    }
    
}
