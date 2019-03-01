package com.github.siilas.weatherfy.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.api.service.GenreSelectorService;
import com.github.siilas.weatherfy.core.exception.WeatherfyException;
import com.github.siilas.weatherfy.openweathermap.client.OpenWeatherMapClient;
import com.github.siilas.weatherfy.spotify.client.SpotifyClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/songs")
public class WeatherfyResource {

    @Autowired
    private SpotifyClient spotifyClient;

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;

    @Autowired
    private GenreSelectorService genreSelectorService;

    @GetMapping("/city/{city}")
    @HystrixCommand(fallbackMethod = "fallback", ignoreExceptions = WeatherfyException.class)
    public Mono<TrackSuggestion> getByCity(@PathVariable String city) {
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

    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    @HystrixCommand(fallbackMethod = "fallback", ignoreExceptions = WeatherfyException.class)
    public Mono<TrackSuggestion> getByLatitudeAndLongitude(@PathVariable Double latitude, @PathVariable Double longitude) {
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

    public TrackSuggestion fallback(String arg) {
        return null;
    }

}
