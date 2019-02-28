package com.github.siilas.weatherfy.openweathermap.client;

import static com.github.siilas.weatherfy.core.http.HttpStatusUtils.isNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.siilas.weatherfy.openweathermap.config.OpenWeatherMapConfig;
import com.github.siilas.weatherfy.openweathermap.exception.CityNotFoundException;
import com.github.siilas.weatherfy.openweathermap.response.City;

@Component
public class OpenWeatherMapClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OpenWeatherMapConfig config;
	
	@Cacheable("cities")
	public City getCityByName(String name) {
		String url = config.getHost().concat("/data/2.5/weather?q=").concat(name)
				.concat("&appid=").concat(config.getId())
				.concat("&units=metric");
		ResponseEntity<City> response = restTemplate.getForEntity(url, City.class);
		if (isNotFound(response)) {
			throw new CityNotFoundException();
		}
		return response.getBody();
    }
	
	@Cacheable("cities")
	public City getCityByLatitudeAndLongitude(Double latitude, Double longitude) {
        String url = config.getHost().concat("/data/2.5/weather?lat=").concat(latitude.toString())
                .concat("&lon=").concat(longitude.toString())
                .concat("&appid=").concat(config.getId())
                .concat("&units=metric");
        ResponseEntity<City> response = restTemplate.getForEntity(url, City.class);
        if (isNotFound(response)) {
            throw new CityNotFoundException();
        }
        return response.getBody();
    }

}
