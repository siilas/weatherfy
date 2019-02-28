package com.github.siilas.weatherfy.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.siilas.weatherfy.openweathermap.client.OpenWeatherMapClient;
import com.github.siilas.weatherfy.openweathermap.response.CityResponse;

@RestController
@RequestMapping("/songs")
public class WeatherfyResource {

	@Autowired
	private OpenWeatherMapClient openWeatherMapClient;
	
    @GetMapping("/city/{cityName}")
    public String getByCity(@PathVariable String cityName) {
    	CityResponse city = openWeatherMapClient.getCityByName(cityName);
    	return city.toString();
    }

    @GetMapping("/latitude/{latitude}/longitude/{longitude}")
    public String getByLatitudeAndLongitude(@PathVariable String latitude, @PathVariable String longitude) {
        return "ok";
    }

}
