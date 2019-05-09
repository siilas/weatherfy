package com.github.siilas.weatherfy.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {
	
	@JsonProperty("lat")
	private double latitude;
	
	@JsonProperty("lon")
	private double longitude;

}
