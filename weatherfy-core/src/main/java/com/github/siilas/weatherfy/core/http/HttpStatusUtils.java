package com.github.siilas.weatherfy.core.http;

import org.springframework.http.HttpStatus;

public final class HttpStatusUtils {

	private HttpStatusUtils() {
		throw new IllegalStateException();
	}
	
	public static boolean isNotFound(HttpStatus status) {
		return status.value() == 404;
	}
	
	public static boolean isNotSuccess(HttpStatus status) {
	    return status.value() != 200;
	}
	
}
