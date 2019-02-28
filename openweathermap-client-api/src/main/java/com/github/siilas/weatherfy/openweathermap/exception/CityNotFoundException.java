package com.github.siilas.weatherfy.openweathermap.exception;

public class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5267425812840487001L;
	
	public CityNotFoundException() {
		super("City not found");
	}

}
