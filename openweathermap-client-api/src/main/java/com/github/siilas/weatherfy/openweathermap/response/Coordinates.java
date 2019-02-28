package com.github.siilas.weatherfy.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Coordinates {
	
	@JsonProperty("lat")
	private double latitude;
	
	@JsonProperty("lon")
	private double longitude;

}
