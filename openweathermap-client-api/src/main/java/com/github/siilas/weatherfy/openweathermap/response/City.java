package com.github.siilas.weatherfy.openweathermap.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.siilas.weatherfy.openweathermap.deserializer.LocalDateTimeDeserializer;

import lombok.Data;

@Data
public class City {

	private String name;

	@JsonProperty("main")
	private Temperature temperature;

	@JsonProperty("coord")
	private Coordinates coordinates;
	
	@JsonProperty("dt")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime date;
	
}
