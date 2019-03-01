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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Mono;

@Api
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
    @ApiOperation("Retorna músicas de acordo com a temperatura")
    @HystrixCommand(fallbackMethod = "fallbackByName", ignoreExceptions = WeatherfyException.class)
    public Mono<TrackSuggestion> getByCity(@PathVariable @ApiParam String city) {
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
    @ApiOperation("Retorna músicas de acordo com a temperatura")
    @HystrixCommand(fallbackMethod = "fallbackByLatitudeAndLongitude", ignoreExceptions = WeatherfyException.class)
    public Mono<TrackSuggestion> getByLatitudeAndLongitude(@PathVariable @ApiParam Double latitude,
            @PathVariable @ApiParam Double longitude) {
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

    public Mono<TrackSuggestion> fallbackByName(String city) {
        return Mono.just(new TrackSuggestion());
    }

    public Mono<TrackSuggestion> fallbackByLatitudeAndLongitude(Double latitude, Double longitude) {
        return Mono.just(new TrackSuggestion());
    }

}
