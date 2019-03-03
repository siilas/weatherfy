package com.github.siilas.weatherfy.openweathermap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.siilas.weatherfy.core.exception.GenericException;
import com.github.siilas.weatherfy.core.http.HttpStatusUtils;
import com.github.siilas.weatherfy.openweathermap.config.OpenWeatherMapConfig;
import com.github.siilas.weatherfy.openweathermap.exception.CityNotFoundException;
import com.github.siilas.weatherfy.openweathermap.response.City;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OpenWeatherMapClient {
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private OpenWeatherMapConfig config;
	
	public static String getPath() {
	    return "/data/2.5/weather";
	}
	
	@Cacheable("cities")
	public Mono<City> getCityByName(String name) {
		return webClient.get()
				.uri(url -> url.host(config.getHost())
						.scheme(config.getScheme())
						.path(getPath())
						.port(config.getPort())
						.queryParam("q", name)
						.queryParam("appid", config.getId())
						.queryParam("units", "metric")
						.build())
				.retrieve()
				.onStatus(HttpStatusUtils::isNotFound, r -> {
					throw new CityNotFoundException();
				})
				.onStatus(HttpStatusUtils::isNotSuccess, r -> {
					throw new GenericException("Error getting city");
				})
				.bodyToMono(City.class)
				.doOnError(error -> log.error("Error getting city by name", error));
    }
	
	@Cacheable("cities")
	public Mono<City> getCityByLatitudeAndLongitude(Double latitude, Double longitude) {
        return webClient.get()
				.uri(url -> url.host(config.getHost())
						.scheme(config.getScheme())
						.path(getPath())
						.port(config.getPort())
						.queryParam("lat", latitude)
						.queryParam("lon", longitude)
						.queryParam("appid", config.getId())
						.queryParam("units", "metric")
						.build())
				.retrieve()
				.onStatus(HttpStatusUtils::isNotFound, r -> {
					throw new CityNotFoundException();
				})
				.onStatus(HttpStatusUtils::isNotSuccess, r -> {
					throw new GenericException("Error getting city");
				})
				.bodyToMono(City.class)
				.doOnError(error -> log.error("Error getting city by latitude and longitude", error));
    }

}
