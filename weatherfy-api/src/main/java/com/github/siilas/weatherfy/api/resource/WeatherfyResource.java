package com.github.siilas.weatherfy.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.api.service.GenreSelectorService;
import com.github.siilas.weatherfy.openweathermap.client.OpenWeatherMapClient;
import com.github.siilas.weatherfy.openweathermap.response.City;
import com.github.siilas.weatherfy.spotify.client.SpotifyClient;
import com.github.siilas.weatherfy.spotify.model.Genre;
import com.github.siilas.weatherfy.spotify.response.Tracks;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
    @HystrixCommand(fallbackMethod = "fallback")
    public TrackSuggestion getByCity(@PathVariable String city) {
        City reponse = openWeatherMapClient.getCityByName(city);
        Genre genre = genreSelectorService.getGenre(reponse.getTemperature());
        Tracks tracks = spotifyClient.getMusicByGenre(genre);
        return TrackSuggestion.builder()
                .city(reponse.getName())
                .latitude(reponse.getCoordinates().getLatitude())
                .longitude(reponse.getCoordinates().getLongitude())
                .requestDate(reponse.getDate())
                .temperature(reponse.getTemperature().getValue())
                .genre(genre)
                .tracks(tracks)
                .build();
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public TrackSuggestion getByLatitudeAndLongitude(@PathVariable Double latitude, @PathVariable Double longitude) {
        City reponse = openWeatherMapClient.getCityByLatitudeAndLongitude(latitude, longitude);
        Genre genre = genreSelectorService.getGenre(reponse.getTemperature());
        Tracks tracks = spotifyClient.getMusicByGenre(genre);
        return TrackSuggestion.builder()
                .city(reponse.getName())
                .latitude(reponse.getCoordinates().getLatitude())
                .longitude(reponse.getCoordinates().getLongitude())
                .requestDate(reponse.getDate())
                .temperature(reponse.getTemperature().getValue())
                .genre(genre)
                .tracks(tracks)
                .build();
    }

    public String fallback() {
        return "Error";
    }

}
