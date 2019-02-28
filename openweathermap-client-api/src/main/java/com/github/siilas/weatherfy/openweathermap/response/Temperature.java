package com.github.siilas.weatherfy.openweathermap.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {

	@JsonProperty("temp")
	private double value;
	
	public boolean isAbove30Degrees() {
		return 30 < value;
	}
	
	public boolean isBetween15And30Degrees() {
		return (14 <= value) && (30 >= value);
	}
	
	public boolean isBetween10And14Degrees() {
		return (10 <= value) && (14 >= value);
	}
	
	public boolean isBelow10Degrees() {
		return 10 > value;
	}
	
}
