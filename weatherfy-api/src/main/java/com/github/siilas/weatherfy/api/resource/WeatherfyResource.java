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
import com.netflix.hystrix.HystrixCommandProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Mono;

@Api
@RestController
@RequestMapping("/songs")
public class WeatherfyResource {

	private static final int HYSTRIX_TIMEOUT = 7000;
	
    @Autowired
    private TracksService tracksService;

    @GetMapping("/city/{city}")
    @ApiOperation("Retorna músicas de acordo com a temperatura")
    public Mono<TrackSuggestion> getByCity(@PathVariable @ApiParam String city) {
        return HystrixCommands.from(tracksService.getByCity(city))
        		.fallback(fallback())
            	.commandName("getByCity")
            	.commandProperties(HystrixCommandProperties
            			.Setter()
            			.withExecutionTimeoutInMilliseconds(HYSTRIX_TIMEOUT))
            	.toMono();
    }

    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    @ApiOperation("Retorna músicas de acordo com a temperatura")
    public Mono<TrackSuggestion> getByLatitudeAndLongitude(@PathVariable @ApiParam Double latitude,
            @PathVariable @ApiParam Double longitude) {
        return HystrixCommands.from(tracksService.getByLatitudeAndLongitude(latitude, longitude))
        		.fallback(fallback())
            	.commandName("getByLatitudeAndLongitude")
            	.commandProperties(HystrixCommandProperties
            			.Setter()
            			.withExecutionTimeoutInMilliseconds(HYSTRIX_TIMEOUT))
            	.toMono();
    }

    public Mono<TrackSuggestion> fallback() {
        return Mono.just(TrackSuggestionFallback.fallBack());
    }

}
