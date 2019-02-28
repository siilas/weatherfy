package com.github.siilas.weatherfy.openweathermap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.siilas.weatherfy.core.http.HttpStatusUtils;
import com.github.siilas.weatherfy.openweathermap.exception.CityNotFoundException;
import com.github.siilas.weatherfy.openweathermap.response.CityResponse;

@Component
public class OpenWeatherMapClient {

	@Value("${openweathermap.app.host}")
	private String host;
	
	@Value("${openweathermap.app.id}")
	private String appId;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public CityResponse getCityByName(String name) {
		String url = host.concat("/data/2.5/weather?q=").concat(name)
				.concat("&appid=").concat(appId)
				.concat("&units=metric");
		ResponseEntity<CityResponse> response = restTemplate.getForEntity(url, CityResponse.class);
		if (HttpStatusUtils.isNotFound(response)) {
			throw new CityNotFoundException();
		}
		return response.getBody();
	}
	
}
