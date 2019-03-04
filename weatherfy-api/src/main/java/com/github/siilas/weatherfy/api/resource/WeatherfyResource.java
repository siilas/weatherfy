package com.github.siilas.weatherfy.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.siilas.weatherfy.api.response.TrackSuggestion;
import com.github.siilas.weatherfy.api.response.TrackSuggestionFallback;
import com.github.siilas.weatherfy.api.service.TracksService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Mono;

@Api
@RestController
@RequestMapping("/songs")
public class WeatherfyResource {

    @Autowired
    private TracksService tracksService;

    @GetMapping("/city/{city}")
    @ApiOperation("Retorna músicas de acordo com a temperatura")
    public Mono<TrackSuggestion> getByCity(@PathVariable @ApiParam String city) {
        return HystrixCommands.from(tracksService.getByCity(city))
        		.fallback(fallbackByName(city))
            	.commandName("getByCity")
            	.toMono();
    }

    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    @ApiOperation("Retorna músicas de acordo com a temperatura")
    public Mono<TrackSuggestion> getByLatitudeAndLongitude(@PathVariable @ApiParam Double latitude,
            @PathVariable @ApiParam Double longitude) {
        return HystrixCommands.from(tracksService.getByLatitudeAndLongitude(latitude, longitude))
        		.fallback(fallbackByLatitudeAndLongitude(latitude, longitude))
            	.commandName("getByLatitudeAndLongitude")
            	.toMono();
    }

    public Mono<TrackSuggestion> fallbackByName(String city) {
        return Mono.just(TrackSuggestionFallback.fallBack());
    }

    public Mono<TrackSuggestion> fallbackByLatitudeAndLongitude(Double latitude, Double longitude) {
        return Mono.just(TrackSuggestionFallback.fallBack());
    }

}
