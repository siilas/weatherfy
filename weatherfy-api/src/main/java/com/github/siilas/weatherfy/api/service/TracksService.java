package com.github.siilas.weatherfy.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.openweathermap.client.OpenWeatherMapClient;
import com.github.siilas.weatherfy.spotify.client.SpotifyClient;

import reactor.core.publisher.Mono;

@Service
public class TracksService {

	@Autowired
    private SpotifyClient spotifyClient;

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;

    @Autowired
    private GenreSelectorService genreSelectorService;
    
    public Mono<TrackSuggestion> getByCity(String city) {
    	TrackSuggestion.Builder builder = new TrackSuggestion.Builder();
    	return openWeatherMapClient.getCityByName(city)
	        .map(info -> {
	            builder.city(info);
	            return genreSelectorService.getGenre(info.getTemperature());
	        })
	        .flatMap(genre -> {
	            builder.genre(genre);
	            return spotifyClient.getMusicByGenre(genre);
	        })
	        .flatMap(tracks -> {
	            builder.tracks(tracks);
	            return Mono.just(builder.build());
	        });
    }
    
    public Mono<TrackSuggestion> getByLatitudeAndLongitude(Double latitude, Double longitude) {
    	TrackSuggestion.Builder builder = new TrackSuggestion.Builder();
    	return openWeatherMapClient.getCityByLatitudeAndLongitude(latitude, longitude)
            .map(info -> {
                builder.city(info);
                return genreSelectorService.getGenre(info.getTemperature());
            })
            .flatMap(genre -> {
                builder.genre(genre);
                return spotifyClient.getMusicByGenre(genre);
            })
            .flatMap(tracks -> {
                builder.tracks(tracks);
                return Mono.just(builder.build());
            });
    }
	
}
