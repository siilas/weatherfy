package com.github.siilas.weatherfy.openweathermap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5267425812840487001L;
	
	public CityNotFoundException() {
		super("City not found");
	}

}
